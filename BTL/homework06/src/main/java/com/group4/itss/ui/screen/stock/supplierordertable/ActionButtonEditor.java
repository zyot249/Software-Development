package com.group4.itss.ui.screen.stock.supplierordertable;

import com.group4.itss.common.AppState;
import com.group4.itss.common.Constants;
import com.group4.itss.ui.Navigator;

import javax.swing.*;
import java.awt.*;

public class ActionButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private AppState appState;
    private Navigator navigator;

    public ActionButtonEditor(JCheckBox checkBox, AppState appState, Navigator navigator) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        this.appState = appState;
        this.navigator = navigator;
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else{
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText( label );
        appState.setCurSupplierOrder(appState.getCurSupplierOrderList().get(row));
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed)  {
            navigator.showPopupScreen("stock-view-supplier-order-popup-screen");
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
