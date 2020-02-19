package com.group4.itss.repository;

import com.group4.itss.entity.model.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Integer> {
    List<SupplierOrder> findAll();

    Optional<SupplierOrder> getByCode(String code);

    @Query(value = "SELECT MAX(id) + 1 FROM supplier_orders", nativeQuery = true)
    Integer getNextId();
}
