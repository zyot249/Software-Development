package com.group4.itss.ui.screen.supplierorder.createsupplierorder;

import com.group4.itss.entity.model.SaleOrderLine;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SaleOrderTableModel extends AbstractTableModel {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private String[] columnNames = {"No.", "Merchandise Code", "Quantity", "Desired Date"};
    private List<SaleOrderLine> saleOrderLines = new ArrayList<>();

    public SaleOrderTableModel(List<SaleOrderLine> saleOrderLines) {
        this.saleOrderLines = saleOrderLines;
    }

    public List<SaleOrderLine> getSaleOrderLines() {
        return saleOrderLines;
    }

    @Override
    public int getRowCount() {
        return saleOrderLines.size();
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
        SaleOrderLine saleOrderLine = saleOrderLines.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return saleOrderLine.getMerchandiseCode();
            case 2:
                return saleOrderLine.getQuantity();
            case 3:
                return saleOrderLine.getDesiredDate() != null ? formatter.format(saleOrderLine.getDesiredDate()): "";
            default:
                return "";
        }
    }
}
