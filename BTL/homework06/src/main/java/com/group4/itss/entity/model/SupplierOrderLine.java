package com.group4.itss.entity.model;

import com.group4.itss.common.Constants;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "supplier_order_lines")
@Data
public class SupplierOrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderCode;
    private String merchandiseCode;
    private Integer quantity;
    private String supplierCode;
    private String checked;

    public SupplierOrderLine() {
        this.checked = Constants.SUPPLIER_ORDER_LINE_UNCHECKED_STATUS;
    }
}
