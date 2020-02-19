package com.group4.itss.controller;

import com.group4.itss.entity.model.SupplierOrderLine;
import com.group4.itss.repository.SupplierOrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("supplier-order-line-controller")
public class SupplierOrderLineController {

    @Autowired
    private SupplierOrderLineRepository supplierOrderLineRepository;

    public List<SupplierOrderLine> getAllLinesByOrderCode(String orderCode) {
        return supplierOrderLineRepository.findByOrderCode(orderCode);
    }

    public List<String> getUncompletedOrderCodes() {
        return supplierOrderLineRepository.getUncompletedOrderCodes();
    }

    public void updateOrderLines(List<SupplierOrderLine> supplierOrderLines) {
        supplierOrderLines.forEach(line -> supplierOrderLineRepository.save(line));
    }
}
