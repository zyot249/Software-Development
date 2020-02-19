package com.group4.itss.ui.screen.saleemployee.tablemodel;

import com.group4.itss.entity.enumeration.SaleOrderLineStatus;
import com.group4.itss.entity.model.SaleOrderLine;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusCellEditor extends AbstractCellEditor implements TableCellEditor , ActionListener {
    private SaleOrderLineStatus status;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
        if(value instanceof SaleOrderLineStatus){
            this.status = (SaleOrderLineStatus) value;
        }
        JComboBox<SaleOrderLineStatus> statusJComboBox = new JComboBox<>();
        statusJComboBox.addItem(SaleOrderLineStatus.PENDING);
        statusJComboBox.addItem(SaleOrderLineStatus.DELETED);

        statusJComboBox.setSelectedItem(value);
        statusJComboBox.addActionListener(this);

        if(isSelected){
            statusJComboBox.setBackground(table.getSelectionBackground());
        }else{
            statusJComboBox.setBackground(table.getSelectionForeground());
        }
        return statusJComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JComboBox<SaleOrderLineStatus> statusJComboBox = (JComboBox<SaleOrderLineStatus>) actionEvent.getSource();
        this.status =(SaleOrderLineStatus) statusJComboBox.getSelectedItem();
    }

    @Override
    public Object getCellEditorValue() {
        return this.status;
    }
}
