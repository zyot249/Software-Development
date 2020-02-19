package com.group4.itss.repository;

import com.group4.itss.entity.enumeration.SaleOrderLineStatus;
import com.group4.itss.entity.model.SaleOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SaleOrderLineRepository extends JpaRepository<SaleOrderLine, Integer> {
    List<SaleOrderLine> findByOrderCode(String orderCode);

    List<SaleOrderLine> findByOrderCodeAndStatus(String orderCode, SaleOrderLineStatus status);

    @Query(nativeQuery = true,
    value = "SELECT order_code FROM sale_order_lines WHERE status != 'DONE' GROUP BY order_code")
    List<String> getUncompletedOrderCode();

    long countByOrderCode(String orderCode);
}
