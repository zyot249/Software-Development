package com.group4.itss.entity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "merchandise")
@Data
public class Merchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String name;
    private String unit;
    private String description;

    @Override
    public String toString() {
        return "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", description='" + description;
    }
}
