package com.group4.itss.controller;

import com.group4.itss.common.AppState;
import com.group4.itss.controller.model.ProvidedMerchandiseResponse;
import com.group4.itss.entity.enumeration.ProvidedMerchandiseStatus;
import com.group4.itss.entity.model.ProvidedMerchandise;
import com.group4.itss.repository.ProvidedMerchandiseRepository;
import com.group4.itss.repository.SaleOrderLineRepository;
import com.group4.itss.repository.SupplierRepository;
import lombok.val;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvidedMerchandiseController {
    private final ProvidedMerchandiseRepository providedMerchandiseRepository;
    private final SaleOrderLineRepository orderLineRepository;
    private final MapperFacade mapperFacade;
    private final SupplierRepository supplierRepository;

    public ProvidedMerchandiseController(ProvidedMerchandiseRepository providedMerchandiseRepository, SaleOrderLineRepository orderLineRepository, MapperFacade mapperFacade, AppState appState, SupplierRepository supplierRepository) {
        this.providedMerchandiseRepository = providedMerchandiseRepository;
        this.orderLineRepository = orderLineRepository;
        this.mapperFacade = mapperFacade;
        this.supplierRepository = supplierRepository;
    }

    public List<ProvidedMerchandise> getByStatus(ProvidedMerchandiseStatus status){
        return providedMerchandiseRepository.findByStatus(status);
    }

    public List<ProvidedMerchandiseResponse> getAllResponse(){
        val merchandise = providedMerchandiseRepository.findByStatus(ProvidedMerchandiseStatus.processing);
        val response = mapperFacade.mapAsList(merchandise, ProvidedMerchandiseResponse.class);
        response.forEach(line->{
            val supplier = supplierRepository.findByCode(line.getSupplierCode());
            line.setTransportation(supplier.getTransportation());
            line.setArrivalDate(line.getCreatedDate().plusDays(supplier.getNumberOfDeliveryDays()));
        });
        return response;
    }

    public List<ProvidedMerchandise> findByOrderCodeAndSupplierCode(String orderCode, String supplierCode){
        return providedMerchandiseRepository.findByOrderCodeAndSupplierCode(orderCode, supplierCode);
    }
    public List<ProvidedMerchandise> findBySupplierCodeAndStatus(String supplierCode, ProvidedMerchandiseStatus status){
        return providedMerchandiseRepository.findBySupplierCodeAndStatus(supplierCode, status);
    }

    public void saveAll(List<ProvidedMerchandise> providedMerchandises){
        providedMerchandiseRepository.saveAll(providedMerchandises);
    }
}
