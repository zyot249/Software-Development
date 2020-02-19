package com.group4.itss.entity.model;

import com.group4.itss.entity.enumeration.SaleOrderLineStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sale_order_lines")
@Data
public class SaleOrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SaleOrderLineStatus status;

    private String orderCode;
    private String merchandiseCode;
    private Integer quantity;
    private Date desiredDate;
}
