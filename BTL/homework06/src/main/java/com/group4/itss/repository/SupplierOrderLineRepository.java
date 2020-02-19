package com.group4.itss.repository;

import com.group4.itss.entity.model.SupplierOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierOrderLineRepository extends JpaRepository<SupplierOrderLine, Integer> {
    List<SupplierOrderLine> findByOrderCode(String id);

    @Query(nativeQuery = true,
            value = "SELECT order_code FROM supplier_order_lines WHERE checked != 'Y' GROUP BY order_code")
    List<String> getUncompletedOrderCodes();
}
