package com.group4.itss.common;

public class Roles {
    public final String ORDER_EMPLOYEE = "ORDER_EMPLOYEE";
    public final String SALE_EMPLOYEE = "SALE_EMPLOYEE";
    public final String STOCK_EMPLOYEE = "STOCK_EMPLOYEE";
    public final String SUPPLIER = "SUPPLIER";

    private Roles() {
    }

    public static Roles getInstance() {
        return new Roles();
    }
}
