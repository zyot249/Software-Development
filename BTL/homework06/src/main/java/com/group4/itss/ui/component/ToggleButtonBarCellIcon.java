package com.group4.itss.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.io.Serializable;
import java.util.Objects;

class ToggleButtonBarCellIcon implements Icon, Serializable {
    private static final long serialVersionUID = 1L;
    private static final int W = 10;
    private static final int H = 21;
    public Shape area;
    public Shape getShape(Container parent, Component c, int x, int y) {
        int w = c.getWidth() - 1;
        int h = c.getHeight() - 1;
        int h2 = (int)(h * .5 + .5);
        int w2 = W;
        Path2D.Float p = new Path2D.Float();
        p.moveTo(0,      0);
        p.lineTo(w - w2, 0);
        p.lineTo(w,      h2);
        p.lineTo(w - w2, h);
        p.lineTo(0,      h);
        if (c != parent.getComponent(0)) {
            p.lineTo(w2, h2);
        }
        p.closePath();
        return AffineTransform.getTranslateInstance(x, y).createTransformedShape(p);
    }
    @Override public void paintIcon(Component c, Graphics g, int x, int y) {
        Container parent = c.getParent();
        if (Objects.isNull(parent)) {
            return;
        }
        area = getShape(parent, c, x, y);

        Color bgc = parent.getBackground();
        Color borderColor = Color.GRAY.brighter();
        if (c instanceof AbstractButton) {
            ButtonModel m = ((AbstractButton) c).getModel();
            if (m.isSelected() || m.isRollover()) {
                bgc = c.getBackground();
                borderColor = Color.GRAY;
            }
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(bgc);
        g2.fill(area);
        g2.setPaint(borderColor);
        g2.draw(area);
        g2.dispose();
    }
    @Override public int getIconWidth() {
        return 150;
    }
    @Override public int getIconHeight() {
        return H;
    }
}
