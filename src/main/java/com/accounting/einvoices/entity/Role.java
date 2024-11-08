package com.accounting.einvoices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@Where(clause = "is_deleted = false")
public class Role extends BaseEntity{

    private String description;

//    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE})
//    List<User> users;
}
