package com.group4.itss.entity.model;

import com.group4.itss.entity.enumeration.ProvidedMerchandiseStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "provided_merchandise")
@Getter
@Setter
public class ProvidedMerchandise {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    private String merchandiseCode;
    private String supplierCode;
    private String unit;
    private Integer quantity;
    private Double price;
    private LocalDate createdDate;
    @Enumerated(EnumType.STRING)
    private ProvidedMerchandiseStatus status;
    private String orderCode;

    @Override
    public String toString() {
        return "merchandiseCode='" + merchandiseCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", price=" + price ;
    }
}
