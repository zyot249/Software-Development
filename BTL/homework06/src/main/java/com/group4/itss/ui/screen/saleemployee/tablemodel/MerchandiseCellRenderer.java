package com.group4.itss.ui.screen.saleemployee.tablemodel;

import com.group4.itss.entity.model.Merchandise;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

class MerchandiseCellRenderer extends DefaultTableCellRenderer {
    public JComponent getTableCellRendererComponent(JTable table, Object value,
                                                    boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Merchandise) {
            Merchandise merchandise = (Merchandise) value;
            setText(merchandise.getName());
        }

        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getSelectionForeground());
        }

        return this;
    }
}
