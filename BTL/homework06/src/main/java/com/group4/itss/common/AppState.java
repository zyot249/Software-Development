package com.group4.itss.common;

import com.group4.itss.entity.model.Account;
import com.group4.itss.entity.model.SaleOrder;
import com.group4.itss.entity.model.SupplierOrder;
import com.group4.itss.entity.model.SaleOrderLine;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter
public class AppState {
    private Account account;
    private SaleOrder currentSaleOrder;
    private SaleOrderLine selectedSaleOrderLine;
    private List<SaleOrder> saleOrderList;
    private List<SupplierOrder> curSupplierOrderList;
    private SupplierOrder curSupplierOrder;
    private Account newAccount;
}
