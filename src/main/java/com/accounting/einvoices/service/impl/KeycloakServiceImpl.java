package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.config.KeycloakProperties;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.service.KeycloakService;
import com.accounting.einvoices.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

import java.util.List;

import static java.util.Arrays.asList;
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
    public Response userCreate(UserDTO dto) {

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(dto.getPassword());

        UserRepresentation keycloakUser = new UserRepresentation();
        keycloakUser.setUsername(dto.getUsername());
        keycloakUser.setFirstName(dto.getFirstName());
        keycloakUser.setLastName(dto.getLastName());
        keycloakUser.setEmail(dto.getUsername());
        keycloakUser.setCredentials(asList(credential));
        keycloakUser.setEmailVerified(true);
        keycloakUser.setEnabled(true);


        Keycloak keycloak = getKeycloakInstance();

        RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
        UsersResource usersResource = realmResource.users();

        // Create Keycloak user
        Response result = usersResource.create(keycloakUser);

        String userId = getCreatedId(result);
        ClientRepresentation appClient = realmResource.clients()
                .findByClientId(keycloakProperties.getClientId()).get(0);

        RoleRepresentation userClientRole = realmResource.clients().get(appClient.getId())
                .roles().get(dto.getRole().getDescription()).toRepresentation();

        realmResource.users().get(userId).roles().clientLevel(appClient.getId())
                .add(asList(userClientRole));


        keycloak.close();
        return result;
    }

    @Override
    public void delete(String username) {

        Keycloak keycloak = getKeycloakInstance();

        RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
        UsersResource usersResource = realmResource.users();

        List<UserRepresentation> userRepresentations = usersResource.search(username);
        String uid = userRepresentations.get(0).getId();
        usersResource.delete(uid);

        keycloak.close();

    }

    @Override
    public UserDTO getLoggedInUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // Check if the principal is of type KeycloakPrincipal
//        if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
//            KeycloakPrincipal<KeycloakSecurityContext> principal =
//                    (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
//            String username = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
//            log.info("logged in user: {}", username);
//            return userService.findByUsername(username);
//        } else if (authentication.getPrincipal() instanceof String) {
//            // Handle case where the principal is a username directly
//            String username = (String) authentication.getPrincipal();
//            log.info("logged in user: {}", username);
//            return userService.findByUsername(username);
//        } else {
//            throw new IllegalStateException("Authentication principal is of an unexpected type.");
//        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount userDetails = (SimpleKeycloakAccount) authentication.getDetails();
        return userService.findByUsername(userDetails.getKeycloakSecurityContext().getToken().getPreferredUsername());
    }

    private Keycloak getKeycloakInstance() {
        return Keycloak.getInstance(keycloakProperties.getAuthServerUrl(),
                keycloakProperties.getMasterRealm(), keycloakProperties.getMasterUser()
                , keycloakProperties.getMasterUserPswd(), keycloakProperties.getMasterClient());
    }
}
