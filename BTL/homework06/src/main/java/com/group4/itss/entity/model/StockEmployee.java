package com.group4.itss.entity.model;

import com.group4.itss.common.Constants;

public class StockEmployee extends Employee {
    private String role;
    public StockEmployee(){
        this.role = Constants.ROLES.STOCK_EMPLOYEE;
    }
}
