package com.group4.itss.repository;

import com.group4.itss.entity.model.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {
    List<SaleOrder> findAll();

    Optional<SaleOrder> getByCode(String code);
}
