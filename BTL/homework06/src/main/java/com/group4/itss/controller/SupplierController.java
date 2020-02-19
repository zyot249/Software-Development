package com.group4.itss.controller;

import com.group4.itss.common.Constants;
import com.group4.itss.entity.model.Supplier;
import com.group4.itss.repository.SupplierRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("supplier-controller")
public class SupplierController {

    public static final String SUPPLIER_CODE_FORMAT = "SUP%05d";

    @Autowired
    private SupplierRepository supplierRepository;

    public void createSupplier(Supplier supplier) {
        Supplier lastSupplier = getLastAddedSupplier();
        if (lastSupplier == null) {
            supplier.setCode(String.format(SUPPLIER_CODE_FORMAT , 1));
        } else {
            int curCode = Integer.parseInt(lastSupplier.getCode().substring(3));
            supplier.setCode(String.format(SUPPLIER_CODE_FORMAT, curCode + 1));
        }
        String password = supplier.getPassword();
        supplier.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(Constants.LOG_ROUND)));
        supplierRepository.save(supplier);
    }

    public void updateSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public Supplier getLastAddedSupplier() {
        Pageable firstPageWithOneElements = PageRequest.of(0, 1);
        List<Supplier> suppliers =  supplierRepository.getLastSupplier(firstPageWithOneElements);
        if (suppliers == null || suppliers.size() == 0) {
            return null;
        } else {
            return suppliers.get(0);
        }
    }

}
