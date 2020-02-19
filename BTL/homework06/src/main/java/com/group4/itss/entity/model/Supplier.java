package com.group4.itss.entity.model;

import com.group4.itss.common.Constants;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "suppliers")
@PrimaryKeyJoinColumn(name = "account_id")
@Getter @Setter
public class Supplier extends Account {
    private String code;
    private String name;
    private String phone;
    private String address;
    private String website;
    private String description;
    private String transportation;
    private Integer numberOfDeliveryDays;

    public Supplier(){
        this.role = Constants.ROLES.SUPPLIER;
    }

    public Supplier(String email,
                    String password,
                    String name,
                    String phone,
                    String address,
                    String website,
                    String description,
                    String transportation,
                    Integer numberOfDeliveryDays) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.website = website;
        this.description = description;
        this.transportation = transportation;
        this.numberOfDeliveryDays = numberOfDeliveryDays;
        this.role = Constants.ROLES.SUPPLIER;
    }
}
