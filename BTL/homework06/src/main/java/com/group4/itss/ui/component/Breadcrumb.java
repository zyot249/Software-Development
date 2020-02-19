package com.group4.itss.ui.component;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Breadcrumb {
    private static final int BORDER = 1;

    private static AbstractButton makeButton(String title, Color color) {
        final ToggleButtonBarCellIcon icon = new ToggleButtonBarCellIcon();
        AbstractButton b = new JRadioButton(title) {
            @Override public boolean contains(int x, int y) {
                if (Objects.nonNull(icon.area)) {
                    return icon.area.contains(x, y);
                } else {
                    return super.contains(x, y);
                }
            }
        };
        b.setIcon(icon);
        b.setContentAreaFilled(false);
        b.setBorder(BorderFactory.createEmptyBorder());
        b.setHorizontalTextPosition(SwingConstants.CENTER);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setBackground(color);
        return b;
    }
    private static JPanel makePanel(int overlap) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING, -overlap, 0)) {
            @Override public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        p.setBorder(BorderFactory.createEmptyBorder(BORDER, overlap + BORDER, BORDER, BORDER));
        p.setOpaque(false);
        return p;
    }

    public static JPanel create(List<String> list) {
        JPanel p = makePanel( 1);
        ButtonGroup bg = new ButtonGroup();
        for (String title : list) {
            AbstractButton b = makeButton(title, Color.ORANGE);
            p.add(b);
            bg.add(b);
            if (list.get(list.size() - 1).equals(title)) {
                b.setSelected(true);
                b.setEnabled(false);
            } else {
                b.setEnabled(false);
                b.setSelected(false);
            }
        }
        return p;
    }
}
