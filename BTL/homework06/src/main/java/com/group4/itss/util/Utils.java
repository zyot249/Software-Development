package com.group4.itss.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class Utils {
    public static void customTable(JTable table, int[] columnWidth) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < columnWidth.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(centerRenderer);
            column.setWidth(columnWidth[i]);
            column.setPreferredWidth(columnWidth[i]);
            column.setMinWidth(columnWidth[i]);
            column.setMaxWidth(columnWidth[i]);
            column.setResizable(false);
        }
        table.setGridColor(Color.BLACK);
    }

    public static NumberFormatter getNumberFormatter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        NumberFormat format = new DecimalFormat("0", symbols);
        format.setMaximumFractionDigits(0);

        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMinimum(1);
        formatter.setMaximum(10000000);
        formatter.setAllowsInvalid(false);
        return formatter;
    }
}
