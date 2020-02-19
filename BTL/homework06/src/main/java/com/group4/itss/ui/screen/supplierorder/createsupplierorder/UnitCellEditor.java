package com.group4.itss.ui.screen.supplierorder.createsupplierorder;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private String selectUnit;

    public static UnitCellEditor getInstance() {
        return new UnitCellEditor();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
        selectUnit = (String) comboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setSelectedItem(this.selectUnit);
        comboBox.addActionListener(this);

        comboBox.addItem("Cai");
        comboBox.addItem("Chiec");
        comboBox.addItem("Tap");
        comboBox.addItem("Quyen");
        comboBox.addItem("Ta");

        if (isSelected) {
            comboBox.setBackground(table.getSelectionBackground());
        } else {
            comboBox.setBackground(table.getSelectionForeground());
        }

        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return selectUnit;
    }
}
