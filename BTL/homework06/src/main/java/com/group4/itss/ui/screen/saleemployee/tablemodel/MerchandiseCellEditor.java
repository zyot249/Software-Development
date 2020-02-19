package com.group4.itss.ui.screen.saleemployee.tablemodel;

import com.group4.itss.entity.model.Merchandise;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MerchandiseCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private Merchandise merchandise;
    private List<Merchandise> merchandiseList;

    public MerchandiseCellEditor(List<Merchandise> merchandiseList) {
        this.merchandiseList = merchandiseList;
    }

    @Override
    public Object getCellEditorValue() {
        return this.merchandise;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        if (value instanceof Merchandise) {
            this.merchandise = (Merchandise) value;
        }
        JComboBox<Merchandise> comboMerchandise = new JComboBox<Merchandise>();

        for (Merchandise theMerchandise : merchandiseList) {
            comboMerchandise.addItem(theMerchandise);
        }

        comboMerchandise.setSelectedItem(merchandise);
        comboMerchandise.addActionListener(this);

        if (isSelected) {
            comboMerchandise.setBackground(table.getSelectionBackground());
        } else {
            comboMerchandise.setBackground(table.getSelectionForeground());
        }

        return comboMerchandise;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<Merchandise> comboMerchandise = (JComboBox<Merchandise>) event.getSource();
        this.merchandise = (Merchandise) comboMerchandise.getSelectedItem();
    }
}
