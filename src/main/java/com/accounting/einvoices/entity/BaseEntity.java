package com.accounting.einvoices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,updatable = false)
    public LocalDateTime insertDateTime;

    @Column(nullable = false,updatable = false)
    public Long insertUserId;

    @Column(nullable = false)
    public LocalDateTime lastUpdateDateTime;

    @Column(nullable = false)
    public Long lastUpdateUserId;

    private Boolean isDeleted = false;

//
//    @PrePersist
//    private void onPrePersist(){
//        this.insertDateTime = LocalDateTime.now();
//        this.lastUpdateDateTime = LocalDateTime.now();
//        this.insertUserId = 1L;
//        this.lastUpdateUserId = 1L;
//    }
//
//    @PreUpdate
//    private void onPreUpdate(){
//        this.lastUpdateDateTime = LocalDateTime.now();
//        this.lastUpdateUserId = 1L;
//    }

//    private Long getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
//                KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) authentication.getPrincipal();
//                KeycloakSecurityContext keycloakSecurityContext = keycloakPrincipal.getKeycloakSecurityContext();
//                String userId = keycloakSecurityContext.getToken().getSubject();
//                return Long.valueOf(userId); // Assuming the user ID can be converted to Long.
//            }
//        }
//        return null; // Or handle this case according application's requirements.
//    }
}
