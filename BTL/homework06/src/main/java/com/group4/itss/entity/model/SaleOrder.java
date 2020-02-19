package com.group4.itss.entity.model;

import com.group4.itss.entity.enumeration.SaleOrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sale_orders")
@Data
public class SaleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String creator;
    private Date createdDate;
    @Enumerated(EnumType.STRING)
    private SaleOrderStatus status;
}
