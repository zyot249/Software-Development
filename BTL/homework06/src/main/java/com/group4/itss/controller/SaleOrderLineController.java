package com.group4.itss.controller;

import com.group4.itss.entity.model.SaleOrder;
import com.group4.itss.entity.model.SaleOrderLine;
import com.group4.itss.repository.ProvidedMerchandiseRepository;
import com.group4.itss.repository.SaleOrderLineRepository;
import com.group4.itss.ui.component.PopUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("sale-order-line-controller")
public class SaleOrderLineController {
    @Autowired
    private SaleOrderLineRepository saleOrderLineRepository;
    @Autowired
    private ProvidedMerchandiseRepository providedMerchandiseRepository;

    public SaleOrderLineController (SaleOrderLineRepository saleOrderLineRepository, ProvidedMerchandiseRepository providedMerchandiseRepository){
        this.saleOrderLineRepository = saleOrderLineRepository;
        this.providedMerchandiseRepository = providedMerchandiseRepository;
    }

    public List<SaleOrderLine> getLinesByOrderCode(String orderCode) {
        try {
            return saleOrderLineRepository.findByOrderCode(orderCode);
        } catch (Exception e){
            System.out.println("Error while querying data");
        }
        return new ArrayList<>();
    }

    public List<String> getUncompletedOrderCode() {
        return saleOrderLineRepository.getUncompletedOrderCode();
    }

    public boolean isSubscribedOrder(String orderCode, String supplierCode){
//        System.out.println("sale order line = "+ saleOrderLineRepository.countByOrderCode(orderCode));
//        System.out.println("provided = "+ providedMerchandiseRepository.countBySupplierCodeAndOrderCode(supplierCode, orderCode));
        return providedMerchandiseRepository.countBySupplierCodeAndOrderCode(supplierCode, orderCode) == saleOrderLineRepository.countByOrderCode(orderCode);
    }

    public boolean saveNewStatus(List<SaleOrderLine> saleOrderLineList){
        try {
            saleOrderLineRepository.saveAll(saleOrderLineList);
            PopUp.render(
                    PopUp.PopupTypes.SUCCESS,
                    "Saving New Status of Sale Order Lines Success",
                    "Success"
            );
            return true;
        } catch (RuntimeException e) {
            PopUp.render(
                    PopUp.PopupTypes.ERROR,
                    "Ops! Something went wrong!",
                    "Error!"
            );
            e.printStackTrace();
        }
        return false;
    }
}
