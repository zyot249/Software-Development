package com.group4.itss.repository;

import com.group4.itss.entity.model.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Integer> {
    Merchandise findMerchandiseByCode(String code);
    List<Merchandise> findAll();
}


