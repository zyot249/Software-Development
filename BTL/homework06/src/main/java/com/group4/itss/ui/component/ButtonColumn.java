package com.group4.itss.ui.component;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    JTable table;
    JButton renderButton;
    JButton editButton;
    String text;
    public ButtonColumn(JTable table, int column)
    {
        super();
        this.table = table;
        renderButton = new JButton();

        editButton = new JButton();
        editButton.setFocusPainted( false );
        editButton.addActionListener( this );

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        fireEditingStopped();
        System.out.println( actionEvent.getActionCommand() + " : " + table.getSelectedRow());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        text = (o == null) ? "" : o.toString();
        editButton.setText( text );
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return text;
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean isSelected, boolean hasFocus, int i, int i1) {
        if (hasFocus)
        {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }
        else if (isSelected)
        {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        }
        else
        {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        renderButton.setText( (o == null) ? "" : o.toString() );
        return renderButton;
    }
}
