package com.group4.itss.ui.screen.stock.supplierordertable;

import com.group4.itss.common.Constants;
import com.group4.itss.entity.model.SupplierOrderLine;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SupplierOrderLineTableModel extends AbstractTableModel {
    private String[] columnNames = {"No.", "Merchandise Code", "Supplier Code", "Quantity", "Checked"};
    private List<SupplierOrderLine> data;

    public SupplierOrderLineTableModel(List<SupplierOrderLine> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SupplierOrderLine supplierOrderLine = data.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return rowIndex + 1;
            }
            case 1: {
                return supplierOrderLine.getMerchandiseCode();
            }
            case 2: {
                return supplierOrderLine.getSupplierCode();
            }
            case 3: {
                return supplierOrderLine.getQuantity();
            }
            case 4: {
                return supplierOrderLine.getChecked().equals(Constants.SUPPLIER_ORDER_LINE_CHECKED_STATUS);
            }
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class clazz = String.class;
        switch (columnIndex) {
            case 0: case 3: {
                clazz = Integer.class;
                break;
            }
            case 4: {
                clazz = Boolean.class;
                break;
            }
        }
        return clazz;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue instanceof Boolean && columnIndex == 4) {
            SupplierOrderLine supplierOrderLine = data.get(rowIndex);
            if ((Boolean) aValue) {
                supplierOrderLine.setChecked(Constants.SUPPLIER_ORDER_LINE_CHECKED_STATUS);
            } else {
                supplierOrderLine.setChecked(Constants.SUPPLIER_ORDER_LINE_UNCHECKED_STATUS);
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    public List<SupplierOrderLine> getData() {
        return data;
    }
}
