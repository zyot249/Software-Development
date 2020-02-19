package com.group4.itss.repository;

import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.Supplier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT s FROM Employee s WHERE s.role = :role ORDER BY s.id DESC")
    List<Employee> getLastEmployeeByRole(@Param("role") String role, Pageable pageable);

    Employee findEmployeeById(int id);
}
