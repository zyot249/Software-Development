package com.group4.itss.controller;

import com.group4.itss.common.Constants;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.repository.EmployeeRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("employee-controller")
public class EmployeeController {

    public static final String STOCK_EMPLOYEE_CODE_FORMAT = "STKE%05d";
    public static final String ORDER_EMPLOYEE_CODE_FORMAT = "ORDE%05d";
    public static final String SALE_EMPLOYEE_CODE_FORMAT = "SALE%05d";

    @Autowired
    private EmployeeRepository employeeRepository;

    public void createEmployee(Employee employee) {
        String role = employee.getRole();
        String roleFormat = "";
        if (role.equals(Constants.ROLES.ORDER_EMPLOYEE)) {
            roleFormat = ORDER_EMPLOYEE_CODE_FORMAT;
        } else if (role.equals(Constants.ROLES.SALE_EMPLOYEE)) {
            roleFormat = SALE_EMPLOYEE_CODE_FORMAT;
        } else if (role.equals(Constants.ROLES.STOCK_EMPLOYEE)) {
            roleFormat = STOCK_EMPLOYEE_CODE_FORMAT;
        }
        Employee lastEmployee = getLastAddedEmployeeByRole(role);
        if (lastEmployee == null) {
            employee.setCode(String.format(roleFormat , 1));
        } else {
            int curCode = Integer.parseInt(lastEmployee.getCode().substring(4));
            employee.setCode(String.format(roleFormat, curCode + 1));
        }
        String password = employee.getPassword();
        employee.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(Constants.LOG_ROUND)));
        employeeRepository.save(employee);
    }

    public Employee getLastAddedEmployeeByRole(String role) {
        Pageable firstPageWithOneElements = PageRequest.of(0, 1);
        List<Employee> employees =  employeeRepository.getLastEmployeeByRole(role, firstPageWithOneElements);
        if (employees == null || employees.size() == 0) {
            return null;
        } else {
            return employees.get(0);
        }
    }
}
