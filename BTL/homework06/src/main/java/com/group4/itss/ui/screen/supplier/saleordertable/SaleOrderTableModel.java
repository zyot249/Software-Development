package com.group4.itss.ui.screen.supplier.saleordertable;

import com.group4.itss.entity.model.SaleOrder;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class SaleOrderTableModel extends AbstractTableModel {

    private String[] columnNames = {"No.", "Order Code", "Creator Code", "Created Date", "Action"};
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private List<SaleOrder> data;

    public SaleOrderTableModel(List<SaleOrder> data) {
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
        SaleOrder saleOrder = data.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return rowIndex + 1;
            }
            case 1: {
                return saleOrder.getCode();
            }
            case 2: {
                return saleOrder.getCreator();
            }
            case 3: {
                if (saleOrder.getCreatedDate() != null) {
                    return formatter.format(saleOrder.getCreatedDate());
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

    public List<SaleOrder> getData() {
        return data;
    }
}
