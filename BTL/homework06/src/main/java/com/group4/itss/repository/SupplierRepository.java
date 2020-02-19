package com.group4.itss.repository;

import com.group4.itss.entity.model.Supplier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository  extends JpaRepository<Supplier, Integer> {
    @Query("SELECT s FROM Supplier s ORDER BY s.id DESC")
    List<Supplier> getLastSupplier(Pageable pageable);

    Supplier findByCode(String code);
}
