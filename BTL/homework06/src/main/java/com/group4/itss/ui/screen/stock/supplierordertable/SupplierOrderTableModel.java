package com.group4.itss.ui.screen.stock.supplierordertable;

import com.group4.itss.entity.model.SupplierOrder;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class SupplierOrderTableModel extends AbstractTableModel {
    private String[] columnNames = {"No.", "Order Code", "Creator Code", "Created Date", "Action"};
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private List<SupplierOrder> data;

    public SupplierOrderTableModel(List<SupplierOrder> data) {
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
        SupplierOrder supplierOrder = data.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return rowIndex + 1;
            }
            case 1: {
                return supplierOrder.getCode();
            }
            case 2: {
                return supplierOrder.getCreator();
            }
            case 3: {
                if (supplierOrder.getCreatedDate() != null) {
                    return formatter.format(supplierOrder.getCreatedDate());
                } else return null;
            }
            case 4: {
                return "View";
            }
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex != 4)
            return false;
        else return true;
    }
}
