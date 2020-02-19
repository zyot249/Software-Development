package com.group4.itss.ui.screen.saleemployee.tablemodel;

import com.group4.itss.common.AppState;
import com.group4.itss.ui.Navigator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    private Navigator navigator;
    private AppState appState;
    protected JButton button;
    private String    label;
    private boolean   isPushed;

    public ButtonEditor(JCheckBox checkBox,Navigator navigator,AppState appState) {
        super(checkBox);
        this.navigator = navigator;
        this.appState = appState;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
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
        label = (value ==null) ? "" : value.toString();
        button.setText( label );
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed)  {
            navigator.navigate("detail-sale-order");
        }
        isPushed = false;
        return new String( label ) ;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}