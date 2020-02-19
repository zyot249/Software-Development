package com.group4.itss.ui.screen.supplierorder.createsupplierorder;

import com.group4.itss.controller.model.ProvidedMerchandiseResponse;
import com.group4.itss.entity.model.ProvidedMerchandise;
import com.group4.itss.repository.ProvidedMerchandiseRepository;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SupplierCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private ProvidedMerchandiseResponse providedMerchandise;
    private List<ProvidedMerchandiseResponse> providedMerchandiseList = new ArrayList<>();

    public SupplierCellEditor(List<ProvidedMerchandiseResponse> providedMerchandiseList) {
        this.providedMerchandiseList =  providedMerchandiseList;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<ProvidedMerchandiseResponse> comboProvidedMerchandise = (JComboBox<ProvidedMerchandiseResponse>) event.getSource();
        this.providedMerchandise = (ProvidedMerchandiseResponse) comboProvidedMerchandise.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof ProvidedMerchandise) {
            this.providedMerchandise = (ProvidedMerchandiseResponse) value;
        }
        JComboBox<ProvidedMerchandiseResponse> comboProvidedMerchandise = new JComboBox<ProvidedMerchandiseResponse>();

        for (ProvidedMerchandiseResponse theProvidedMerchandise : providedMerchandiseList) {
            comboProvidedMerchandise.addItem(theProvidedMerchandise);
        }

        comboProvidedMerchandise.setSelectedItem(providedMerchandise);
        comboProvidedMerchandise.addActionListener(this);

        if (isSelected) {
            comboProvidedMerchandise.setBackground(table.getSelectionBackground());
        } else {
            comboProvidedMerchandise.setBackground(table.getSelectionForeground());
        }

        return comboProvidedMerchandise;
    }

    @Override
    public Object getCellEditorValue() {
        return this.providedMerchandise;
    }
}
