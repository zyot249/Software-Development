package com.group4.itss.controller;

import com.group4.itss.entity.model.SupplierOrder;
import com.group4.itss.repository.SupplierOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("supplier-order-controller")
public class SupplierOrderController {
    public static final String SUPPLIER_ORDER_CODE_FORMAT = "SUPORDER%05d";

    @Autowired
    private SupplierOrderRepository supplierOrderRepository;

    public List<SupplierOrder> getOrderByOrderCodeList(List<String> orderCodeList) {
        List<SupplierOrder> supplierOrders = new ArrayList<>();

        orderCodeList.forEach(code -> {
            Optional<SupplierOrder> supplierOrder = supplierOrderRepository.getByCode(code);
            supplierOrder.ifPresent(supplierOrders::add);
        });

        return supplierOrders;
    }

    public String getNextSupplierOrderCode() {
        Integer nextID = supplierOrderRepository.getNextId();
        if (nextID != null) {
            return String.format(SUPPLIER_ORDER_CODE_FORMAT, nextID);
        }
        return String.format(SUPPLIER_ORDER_CODE_FORMAT, 1);
    }

    public List<SupplierOrder> getAllOrder() {
        return supplierOrderRepository.findAll();
    }
}
