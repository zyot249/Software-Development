package com.group4.itss.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "site_order")
public class SiteOrder {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "creator")
    private String creator;

    @Column(name = "create_at")
    private Date createAt;
}
