package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.config.KeycloakProperties;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.exception.user.UserNotFoundException;
import com.accounting.einvoices.service.KeycloakService;
import com.accounting.einvoices.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.keycloak.admin.client.CreatedResponseUtil.getCreatedId;

@Slf4j
@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final KeycloakProperties keycloakProperties;
    private final UserService userService;

    public KeycloakServiceImpl(KeycloakProperties keycloakProperties, UserService userService) {
        this.keycloakProperties = keycloakProperties;
        this.userService = userService;
    }

    @Override
    public void userCreate(UserDTO dto) {

        UserRepresentation keycloakUser = getUserRepresentation(dto);

        Keycloak keycloak = getKeycloakInstance();

        RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
        UsersResource usersResource = realmResource.users();

        try {

            Response result = usersResource.create(keycloakUser);

            if (Objects.equals(201, result.getStatus())) {
                String userId = getCreatedId(result);

                ClientRepresentation appClient = realmResource.clients()
                        .findByClientId(keycloakProperties.getClientId()).get(0);

                RoleRepresentation userClientRole = realmResource.clients()
                        .get(appClient.getId()).roles().get(dto.getRole().getDescription()).toRepresentation();

                realmResource.users().get(userId).roles().clientLevel(appClient.getId())
                        .add(Collections.singletonList(userClientRole));
                try {
                    emailVerification(userId);
                    log.info("Verification email was sent!!!");
                } catch (Exception e) {
                    log.error("Email verification Could not send: {}", e.getMessage());
                }

            }

        } catch (Exception e) {
            log.error("Exception in keycloak service user create: {}", e.getMessage());
        } finally {
            keycloak.close();
        }
    }


    @Override
    public void userUpdate(UserDTO dto) {

        try (Keycloak keycloak = getKeycloakInstance()) {

            RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
            UsersResource usersResource = realmResource.users();

            List<UserRepresentation> userRepresentations = usersResource.search(dto.getUsername());

            if (userRepresentations.isEmpty()) {
                throw new UserNotFoundException("User does not exist.");
            }

            UserRepresentation keycloakUser = userRepresentations.get(0);

            updateRoles(realmResource, keycloakUser.getId(), dto.getRole().getDescription());

            keycloakUser.setFirstName(dto.getFirstName());
            keycloakUser.setLastName(dto.getLastName());

            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                updatePassword(usersResource, keycloakUser.getId(), dto.getPassword());
            }

            usersResource.get(keycloakUser.getId()).update(keycloakUser);

        }
    }

    private void updateRoles(RealmResource realmResource, String userId, String role) {

        ClientRepresentation appClient = realmResource.clients()
                .findByClientId(keycloakProperties.getClientId()).get(0);

        String clientId = appClient.getId();

        List<RoleRepresentation> existingRoles = realmResource.users().get(userId)
                .roles().clientLevel(clientId).listEffective();
        existingRoles.forEach(existingRole -> realmResource.users().get(userId)
                .roles().clientLevel(clientId).remove(Collections.singletonList(existingRole)));

        RoleRepresentation userClientRole = realmResource.clients().get(clientId)
                .roles().get(role).toRepresentation();

        realmResource.users().get(userId).roles().clientLevel(clientId)
                .add(Collections.singletonList(userClientRole));

    }

    private void updatePassword(UsersResource usersResource, String userId, String newPassword) {

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(newPassword);

        usersResource.get(userId).resetPassword(credential);

    }

    private UserRepresentation getUserRepresentation(UserDTO dto) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(dto.getPassword());

        UserRepresentation keycloakUser = new UserRepresentation();
        keycloakUser.setUsername(dto.getUsername());
        keycloakUser.setFirstName(dto.getFirstName());
        keycloakUser.setLastName(dto.getLastName());
        keycloakUser.setEmail(dto.getUsername());
        keycloakUser.setCredentials(List.of(credential));
        keycloakUser.setEmailVerified(false);
        keycloakUser.setEnabled(true);
        return keycloakUser;
    }

    @Override
    public void userDelete(String username) {

        Keycloak keycloak = getKeycloakInstance();

        RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
        UsersResource usersResource = realmResource.users();

        List<UserRepresentation> userRepresentations = usersResource.search(username);
        String uid = userRepresentations.get(0).getId();
        usersResource.delete(uid);

        keycloak.close();

    }

    @Override
    public void emailVerification(String userId) {
        Keycloak keycloak = getKeycloakInstance();
        RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
        UsersResource usersResource = realmResource.users();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public boolean isEmailVerified(String username) {
        Keycloak keycloak = getKeycloakInstance();
        RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
        UsersResource usersResource = realmResource.users();
        List<UserRepresentation> users = usersResource.search(username);
        if (users.isEmpty()) {
            throw new UserNotFoundException("User not found to verify email.");
        }
        UserRepresentation user = users.get(0);
        return user.isEmailVerified();
    }

    @Override
    public UserDTO getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount userDetails = (SimpleKeycloakAccount) authentication.getDetails();
        log.info("\n\n>>>> Found userDTO: {}", userService.findByUsername(userDetails.getKeycloakSecurityContext().getToken().getPreferredUsername()));
        return userService.findByUsername(userDetails.getKeycloakSecurityContext().getToken().getPreferredUsername());
    }

    private Keycloak getKeycloakInstance() {
        return Keycloak.getInstance(keycloakProperties.getAuthServerUrl(),
                keycloakProperties.getMasterRealm(), keycloakProperties.getMasterUser()
                , keycloakProperties.getMasterUserPswd(), keycloakProperties.getMasterClient());
    }
}
