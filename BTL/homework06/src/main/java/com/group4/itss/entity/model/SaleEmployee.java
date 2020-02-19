package com.group4.itss.entity.model;

import com.group4.itss.common.Constants;

public class SaleEmployee extends Employee {
    private String role;
    public SaleEmployee(){
        this.role = Constants.ROLES.SALE_EMPLOYEE;
    }
}
