package com.group4.itss.ui.screen.supplierorder;

import com.group4.itss.entity.model.SupplierOrderLine;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DetailSupplierOrderModel extends AbstractTableModel {
    private String columnAttrs[] = {"No.","Order code","Supplier code","Merchandise code","Quantity"};
    List<SupplierOrderLine> supplierOrderLineList = new ArrayList<>();

    public DetailSupplierOrderModel(List<SupplierOrderLine> supplierOrderLineList){
        this.supplierOrderLineList = supplierOrderLineList;
    }

    @Override
    public int getRowCount() {
        return supplierOrderLineList.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        SupplierOrderLine supplierOrderLine = supplierOrderLineList.get(rowIndex);
        switch (columnIndex){
            case 0: return rowIndex+1;
            case 1: return supplierOrderLine.getOrderCode();
            case 2: return supplierOrderLine.getSupplierCode();
            case 3: return supplierOrderLine.getMerchandiseCode();
            case 4: return supplierOrderLine.getQuantity();
            default: return "";
        }
    }
}
