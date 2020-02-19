package com.group4.itss.entity.model;

import com.group4.itss.common.Constants;
import lombok.*;

import javax.persistence.*;

public class OrderEmployee extends Employee {
    private String role;
    public OrderEmployee() {
        this.role = Constants.ROLES.ORDER_EMPLOYEE;
    }
}
