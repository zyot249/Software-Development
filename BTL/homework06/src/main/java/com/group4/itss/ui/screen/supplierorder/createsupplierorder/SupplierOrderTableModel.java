package com.group4.itss.ui.screen.supplierorder.createsupplierorder;

import com.group4.itss.controller.model.ProvidedMerchandiseResponse;
import com.group4.itss.entity.model.ProvidedMerchandise;
import com.group4.itss.entity.model.SupplierOrderLine;
import com.group4.itss.ui.component.PopUp;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderTableModel extends AbstractTableModel {
    private String[] columnNames = {"No.", "Merchandise Code", "Supplier", "Quantity"};
    private List<SupplierOrderLine> supplierOrderLineList = new ArrayList<>();
    private List<ProvidedMerchandise> providedMerchandiseList = new ArrayList<>();

    public List<SupplierOrderLine> getSupplierOrderLineList() {
        return supplierOrderLineList;
    }

    public List<ProvidedMerchandise> getProvidedMerchandiseList() {
        return providedMerchandiseList;
    }

    public void addRow(SupplierOrderLine supplierOrderLine) {
        supplierOrderLineList.add(supplierOrderLine);
        super.fireTableRowsInserted(supplierOrderLineList.size() - 1, supplierOrderLineList.size() - 1);
    }

    @Override
    public int getRowCount() {
        return supplierOrderLineList.size();
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
        SupplierOrderLine supplierOrderLine = supplierOrderLineList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return supplierOrderLine.getMerchandiseCode();
            case 2:
                return supplierOrderLine.getSupplierCode();
            case 3:
                return supplierOrderLine.getQuantity();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        SupplierOrderLine supplierOrderLine = supplierOrderLineList.get(rowIndex);
        switch (columnIndex) {
            case 2: {
                ProvidedMerchandiseResponse providedMerchandiseResponse  = (ProvidedMerchandiseResponse) value;
                if (!supplierOrderLine.getMerchandiseCode().equals(providedMerchandiseResponse.getMerchandiseCode())) {
                    PopUp.render(
                            PopUp.PopupTypes.ERROR,
                            "Please! Select the right merchandise",
                            "Submit Supplier Order!"
                    );
                    return;
                }

                supplierOrderLine.setSupplierCode(providedMerchandiseResponse.getSupplierCode());
                providedMerchandiseList.add(providedMerchandiseResponse.getProvidedMerchandise());
                break;
            }
            case 3:
                int buyQuantity = Integer.parseInt((String) value);
                ProvidedMerchandise providedMerchandise = providedMerchandiseList.get(rowIndex);
                if (buyQuantity < 0) {
                    PopUp.render(
                            PopUp.PopupTypes.ERROR,
                            "The quantity can not be a negative number!",
                            "Submit Supplier Order!"
                    );
                    return;
                }

                if (buyQuantity > providedMerchandise.getQuantity()) {
                    PopUp.render(
                            PopUp.PopupTypes.ERROR,
                            "Sorry! The quantity in stock is not enough!",
                            "Submit Supplier Order!"
                    );
                    return;
                }

                supplierOrderLine.setQuantity(buyQuantity);
                break;
        }
        super.fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2 || columnIndex == 3;
    }
}
