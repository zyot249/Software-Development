package com.group4.itss.entity.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String email;
    protected String password;
    protected String role;
}
