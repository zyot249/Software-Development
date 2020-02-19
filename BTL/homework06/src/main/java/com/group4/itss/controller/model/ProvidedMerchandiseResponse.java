package com.group4.itss.controller.model;

import com.group4.itss.entity.enumeration.ProvidedMerchandiseStatus;
import com.group4.itss.entity.model.ProvidedMerchandise;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@Getter
@Setter
public class ProvidedMerchandiseResponse {
    private Integer id;
    private String merchandiseCode;
    private String supplierCode;
    private String unit;
    private Integer quantity;
    private Double price;
    private LocalDate createdDate;
    private String transportation;
    private ProvidedMerchandiseStatus status;
    private LocalDate arrivalDate;
    private String orderCode;

    public ProvidedMerchandise getProvidedMerchandise() {
        ProvidedMerchandise providedMerchandise = new ProvidedMerchandise();
        providedMerchandise.setId(this.id);
        providedMerchandise.setSupplierCode(this.supplierCode);
        providedMerchandise.setQuantity(this.quantity);
        providedMerchandise.setPrice(this.price);
        providedMerchandise.setStatus(this.status);
        providedMerchandise.setCreatedDate(this.createdDate);
        providedMerchandise.setOrderCode(this.orderCode);
        providedMerchandise.setUnit(this.unit);
        providedMerchandise.setMerchandiseCode(this.merchandiseCode);
        return providedMerchandise;
    }

    @Override
    public String toString() {
        return "supplierCode='" + supplierCode + '\'' +
                "merchandiseCode='" + merchandiseCode + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", transportation='" + transportation + '\'' +
                ", arrivalDate=" + arrivalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ;
    }
}
