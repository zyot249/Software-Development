package com.group4.itss.ui.screen.supplier.saleordertable;

import com.group4.itss.entity.model.SaleOrderLine;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class SaleOrderLineTableModel extends AbstractTableModel {
    private String[] columnNames = {"No.", "Merchandise Code", "Quantity", "Desired Date", "Status"};
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private List<SaleOrderLine> data;

    public SaleOrderLineTableModel(List<SaleOrderLine> data) {
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
        SaleOrderLine saleOrderLine = data.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return rowIndex + 1;
            }
            case 1: {
                return saleOrderLine.getMerchandiseCode();
            }
            case 2: {
                return saleOrderLine.getQuantity();
            }
            case 3: {
                if (saleOrderLine.getDesiredDate() != null) {
                    return formatter.format(saleOrderLine.getDesiredDate());
                } else return null;
            }
            case 4: {
                return saleOrderLine.getStatus();
            }
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
