package com.group4.itss.ui.screen.saleemployee.tablemodel;

import com.group4.itss.controller.MerchandiseController;
import com.group4.itss.controller.SaleOrderController;
import com.group4.itss.entity.enumeration.SaleOrderLineStatus;
import com.group4.itss.entity.model.Merchandise;
import com.group4.itss.entity.model.SaleOrder;
import com.group4.itss.entity.model.SaleOrderLine;
import com.group4.itss.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DetailSaleOrderModel extends AbstractTableModel {
    @Autowired
    private MerchandiseController merchandiseController;

    private String columnAttrs[] = {"No.","Sale Order Code","Merchandise code","Quantity","Unit","Status"};

    private List<SaleOrderLine> saleOrderLines = new ArrayList<>();

    public DetailSaleOrderModel(List<SaleOrderLine> saleOrderLines, MerchandiseController merchandiseController){
        this.saleOrderLines = saleOrderLines;
        this.merchandiseController = merchandiseController;
    }

    public List<SaleOrderLine> getSaleOrderLineList(){
        return this.saleOrderLines;
    }

    @Override
    public int getRowCount() {
        return saleOrderLines.size();
    }

    @Override
    public int getColumnCount() {
        return columnAttrs.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnAttrs[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        SaleOrderLine saleOrderLine = saleOrderLines.get(row);
        switch (column){
            case 0: return row+1;
            case 1: return saleOrderLine.getOrderCode();
            case 2: return saleOrderLine.getMerchandiseCode();
            case 3: return saleOrderLine.getQuantity();
            case 4: return merchandiseController.findMerchandiseByOrderCode(saleOrderLine.getMerchandiseCode()).getUnit();
            case 5: return saleOrderLine.getStatus().toString();
            default: return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        SaleOrderLine saleOrderLine = saleOrderLines.get(rowIndex);
        System.out.println(saleOrderLine);
        switch (columnIndex) {
            case 5:
                saleOrderLine.setStatus((SaleOrderLineStatus) aValue);
        }
        super.fireTableCellUpdated(rowIndex, columnIndex);
    }
}
