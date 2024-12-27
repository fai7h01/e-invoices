package com.accounting.einvoices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verification_token")
public class VerificationToken extends BaseEntity {

    private String token;
    private String email;
    private LocalDate expiryDate;

    public boolean isValid() {
        LocalDate now = LocalDate.now();
        return expiryDate.isAfter(now) && !super.getIsDeleted();
    }

    public VerificationToken(String email) {
        this.email = email;
        this.expiryDate = LocalDate.now().plusDays(1);
        this.token = UUID.randomUUID().toString();
    }

}
