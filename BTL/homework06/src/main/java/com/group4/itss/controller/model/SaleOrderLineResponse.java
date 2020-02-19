package com.group4.itss.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SaleOrderLineResponse {
    private Integer id;
    private String orderCode;
    private String merchandiseCode;
    private Integer quantity;
    private String status;
    private Date desiredDate;
    private String name;
    private String unit;
}
