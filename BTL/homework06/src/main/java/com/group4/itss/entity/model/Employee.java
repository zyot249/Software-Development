package com.group4.itss.entity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "account_id")
@Getter @Setter
@NoArgsConstructor
public class Employee extends Account {
    private String name;
    private Date dob;
    private int gender;
    private String code;

    public Employee(
            String email,
            String password,
            String role,
            String name,
            Date dob,
            int gender
    ) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.dob = dob;
        this.name = name;
        this.gender = gender;
    }
}
