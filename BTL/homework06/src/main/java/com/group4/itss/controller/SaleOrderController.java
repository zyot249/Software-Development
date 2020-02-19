package com.group4.itss.controller;

import com.group4.itss.controller.model.SaleOrderLineResponse;
import com.group4.itss.entity.enumeration.SaleOrderLineStatus;
import com.group4.itss.entity.model.SaleOrder;
import com.group4.itss.entity.model.SaleOrderLine;
import com.group4.itss.repository.MerchandiseRepository;
import com.group4.itss.repository.SaleOrderLineRepository;
import com.group4.itss.repository.SaleOrderRepository;
import com.group4.itss.ui.component.PopUp;
import com.mchange.lang.IntegerUtils;
import lombok.val;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component("sale-order-controller")
public class SaleOrderController {
    @Autowired
    private final MerchandiseRepository merchandiseRepository;
    @Autowired
    private final SaleOrderLineRepository saleOrderLineRepository;
    @Autowired
    private SaleOrderRepository saleOrderRepository;
    private final MapperFacade mapperFacade;

    public static final String SALE_ORDER_CODE_FORMAT = "SO%05d";

    public SaleOrderController(MerchandiseRepository merchandiseRepository,
                               SaleOrderLineRepository saleOrderLineRepository,
                               MapperFacade mapperFacade,
                               SaleOrderRepository saleOrderRepository) {
        this.merchandiseRepository = merchandiseRepository;
        this.saleOrderLineRepository = saleOrderLineRepository;
        this.mapperFacade = mapperFacade;
        this.saleOrderRepository = saleOrderRepository;
    }

    public List<SaleOrder> getAllSaleOrders() {
        try {
            return saleOrderRepository.findAll();
        } catch (Exception e){
            System.out.println("Error while querying data");
        }
        return new ArrayList<>();
    }

    public String createSaleOrderCode() {
        String code;
        do {
            code =String.format(SALE_ORDER_CODE_FORMAT, (new Random()).nextInt(10000));
        }while(compareCode(code,this.getAllSaleOrders()));
        return code;
    }

    private boolean compareCode(String code, List<SaleOrder> saleOrderList){
        for (int i = 0; i < saleOrderList.size(); i++) {
            if(code.equals(saleOrderList.get(i).getCode())){
                return true;
            }
        }
        return false;
    }

    public List<SaleOrderLineResponse> getSaleOrderResponseByOrderCode(String orderCode) {
        val saleOrderLines = saleOrderLineRepository.findByOrderCodeAndStatus(orderCode, SaleOrderLineStatus.PENDING);
        val listResponse = mapperFacade.mapAsList(saleOrderLines, SaleOrderLineResponse.class);
        listResponse.forEach(response -> {
            val merchandise = merchandiseRepository.findMerchandiseByCode(response.getMerchandiseCode());
            response.setUnit(merchandise.getUnit());
            response.setName(merchandise.getName());
        });
        return listResponse;
    }

    public List<SaleOrder> getSaleOrdersByOrderCodeList(List<String> orderCodeList) {
        List<SaleOrder> saleOrderList = new ArrayList<>();

        orderCodeList.forEach(code -> {
            Optional<SaleOrder> saleOrder = saleOrderRepository.getByCode(code);
            saleOrder.ifPresent(saleOrderList::add);
        });
        return saleOrderList;
    }

    public boolean checkValidSaleOrder(List<SaleOrderLine> saleOrderLines){
        for(SaleOrderLine saleOrderLine : saleOrderLines){
            if(StringUtils.isBlank(saleOrderLine.getMerchandiseCode())) return false;
            if(saleOrderLine.getQuantity() == null) return false;
            if(saleOrderLine.getDesiredDate() == null) return false;
        }
        return true;
    }

    public boolean updateNewSaleOrder(SaleOrder saleOrder,List<SaleOrderLine> saleOrderLineList){
        try {
            saleOrderRepository.save(saleOrder);
            saleOrderLineRepository.saveAll(saleOrderLineList);
            PopUp.render(
                    PopUp.PopupTypes.SUCCESS,
                    "Create Sale Order Success",
                    "Sale Order"
            );
            return true;
        } catch (RuntimeException e) {
            PopUp.render(
                    PopUp.PopupTypes.ERROR,
                    "Ops! Something went wrong!",
                    "Sale Order"
            );
            e.printStackTrace();
        }
        return false;
    }
}
