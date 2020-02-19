package com.group4.itss.repository;

import com.group4.itss.entity.enumeration.ProvidedMerchandiseStatus;
import com.group4.itss.entity.model.ProvidedMerchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvidedMerchandiseRepository extends JpaRepository<ProvidedMerchandise, Integer> {

    List<ProvidedMerchandise> findByOrderCodeAndSupplierCode(String orderCode, String supplierCode);

    List<ProvidedMerchandise> findBySupplierCodeAndStatus(String supplierCode, ProvidedMerchandiseStatus status);

    List<ProvidedMerchandise> findByStatus(ProvidedMerchandiseStatus status);

    long countBySupplierCodeAndOrderCode(String supplierCode, String orderCode);
}
