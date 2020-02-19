package com.group4.itss.ui.screen.saleemployee.tablemodel;

import com.group4.itss.entity.model.Merchandise;
import com.group4.itss.ui.component.PopUp;
import org.apache.commons.lang3.StringUtils;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MerchandiseTableModel extends AbstractTableModel {
    private String columnAttrs[] = {"No.","Merchandise code","Merchandise name","Unit","Description"};
    private List<Merchandise> merchandiseList = new ArrayList<>();
    public MerchandiseTableModel(List<Merchandise> merchandiseList){
        this.merchandiseList = merchandiseList;
    }

    public List<Merchandise> getMerchandiseList(){
        return this.merchandiseList;
    }

    public void addRow(){
        merchandiseList.add(new Merchandise());
        super.fireTableRowsInserted(merchandiseList.size()-1,merchandiseList.size());
    }

    @Override
    public int getRowCount() {
        return merchandiseList.size();
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
        Merchandise merchandise = this.merchandiseList.get(row);
        switch (column){
            case 0: return row + 1;
            case 1: return merchandise.getCode();
            case 2: return merchandise.getName();
            case 3: return merchandise.getUnit();
            case 4: return merchandise.getDescription();
            default: return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Merchandise merchandise = merchandiseList.get(rowIndex);
        String value = (String) aValue;
        switch (columnIndex){
            case 1:
                if(StringUtils.isBlank(value)){
                    PopUp.render(PopUp.PopupTypes.ERROR,"You cannot leave code field empty!","Error");
                    break;
                }
                merchandise.setCode(value);
                break;
            case 2:
                if(StringUtils.isBlank(value)){
                    PopUp.render(PopUp.PopupTypes.ERROR,"You cannot leave name field empty!","Error");
                    break;
                }
                merchandise.setName(value);
                break;
            case 3:
                if(StringUtils.isBlank(value)){
                    PopUp.render(PopUp.PopupTypes.ERROR,"You cannot leave unit field empty!","Error");
                    break;
                }
                merchandise.setUnit(value);
                break;
            case 4:
                merchandise.setDescription(value);
                break;
        }
        super.fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }
}
