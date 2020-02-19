package com.group4.itss.ui.screen.saleemployee.tablemodel;

import com.group4.itss.entity.model.Merchandise;
import com.group4.itss.entity.model.SaleOrderLine;
import com.group4.itss.ui.component.PopUp;
import org.apache.commons.lang3.StringUtils;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SaleOrderTableModel extends AbstractTableModel {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private String[] columnNames = {"No.", "Merchandise Name", "Merchandise Code", "Quantity", "Desired Date"};
    private List<SaleOrderLine> saleOrderLineList = new ArrayList<>();
    private List<Merchandise> merchandiseList = new ArrayList<>();

    public void addRow(SaleOrderLine saleOrderLine) {
        saleOrderLineList.add(saleOrderLine);
        merchandiseList.add(new Merchandise());
        super.fireTableRowsInserted(saleOrderLineList.size() - 1, saleOrderLineList.size() - 1);
    }

    public List<SaleOrderLine> getSaleOrderLineList() {
        return this.saleOrderLineList;
    }

    public List<Merchandise> getMerchandiseList() {
        return merchandiseList;
    }

    @Override
    public int getRowCount() {
        return saleOrderLineList.size();
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
        SaleOrderLine saleOrderLine = saleOrderLineList.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return merchandiseList.get(rowIndex).getName();
            case 2:
                return saleOrderLine.getMerchandiseCode();
            case 3:
                return saleOrderLine.getQuantity();
            case 4:
                if (saleOrderLine.getDesiredDate() == null) {
                    return "";
                }
                return formatter.format(saleOrderLine.getDesiredDate());
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        SaleOrderLine saleOrderLine = saleOrderLineList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                merchandiseList.set(rowIndex, (Merchandise) value);
                saleOrderLine.setMerchandiseCode(((Merchandise) value).getCode());
                break;
            case 3:
                if(StringUtils.isBlank((String) value)){
                    PopUp.render(PopUp.PopupTypes.ERROR,"You can't leave quantity field empty","Warning");
                    break;
                }
                Integer val;
                try {
                    val = Integer.parseInt((String) value);
                } catch (NumberFormatException e){
                    PopUp.render(PopUp.PopupTypes.ERROR,"Quantity is not in number format","Error!");
                    break;
                }
                if(val<1){
                    PopUp.render(PopUp.PopupTypes.ERROR,"Quantity must not fewer than 1","Error!");
                    break;
                }
                saleOrderLine.setQuantity(val);
                break;
            case 4:
                if(StringUtils.isBlank((String) value)){
                    PopUp.render(PopUp.PopupTypes.ERROR,"You can't leave desired date field empty","Warning");
                    break;
                }
                try {
                    saleOrderLine.setDesiredDate(formatter.parse((String) value));
                } catch (ParseException e) {
                    PopUp.render(
                            PopUp.PopupTypes.ERROR,
                            "Date format must be dd/MM/yyyy",
                            "Create Merchandise"
                    );
                }
        }
        super.fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1 || columnIndex == 3 || columnIndex == 4;
    }
}
