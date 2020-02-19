/*
 * Created by JFormDesigner on Sun Dec 08 16:18:15 ICT 2019
 */

package com.group4.itss.ui.screen.supplierorder;

import com.group4.itss.common.AppState;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

@Component("order-employee-home-screen")
public class OrderEmployeeHome extends BaseScreen {
    private Navigator navigator;
    private AppState appState;

    public OrderEmployeeHome(Navigator navigator, AppState appState) {
        this.navigator = navigator;
        this.appState = appState;
        initComponents();
        customize();
    }

    @Override
    public void display() {
        Employee supplier = (Employee) appState.getAccount();
        lbName.setText(supplier.getName());
    }

    private void customize(){
        panel2.add(Breadcrumb.create(Arrays.asList("Home")));
    }

    private void onGotoSaleOrderListScreen(MouseEvent e) {
        navigator.navigate("view-sale-order");
    }


    private void onGotoSupplierOrderListScreen(MouseEvent e) {
        navigator.navigate("supplier-order-list-screen");
    }

    private void btLogoutMouseClicked(MouseEvent e) {
        navigator.onLogout();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Thanh Dao Ngoc
        panel2 = new JPanel();
        panel3 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        header = new JPanel();
        lbName = new JLabel();
        lbRole = new JLabel();
        btnLogout = new JButton();
        separator2 = new JSeparator();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
        swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border
        .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog"
        ,java.awt.Font.BOLD,12),java.awt.Color.red), getBorder
        ())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
        .beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException
        ();}});
        setLayout(null);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(1, 2));
        }
        add(panel2);
        panel2.setBounds(5, 75, 765, 41);

        //======== panel3 ========
        {

            //---- button1 ----
            button1.setText("View Sale Order");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onGotoSaleOrderListScreen(e);
                }
            });

            //---- button2 ----
            button2.setText("View Supplier Order");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onGotoSupplierOrderListScreen(e);
                }
            });

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(188, Short.MAX_VALUE))
            );
        }
        add(panel3);
        panel3.setBounds(new Rectangle(new Point(6, 111), panel3.getPreferredSize()));

        //======== header ========
        {
            header.setBorder(LineBorder.createBlackLineBorder());
            header.setLayout(null);

            //---- lbName ----
            lbName.setText("Tran Bich Ngoc");
            lbName.setHorizontalAlignment(SwingConstants.RIGHT);
            header.add(lbName);
            lbName.setBounds(440, 5, 350, 25);

            //---- lbRole ----
            lbRole.setText("Supplier Employee");
            lbRole.setHorizontalAlignment(SwingConstants.RIGHT);
            header.add(lbRole);
            lbRole.setBounds(670, 30, 120, 25);

            //---- btnLogout ----
            btnLogout.setText("Logout");
            btnLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btLogoutMouseClicked(e);
                }
            });
            header.add(btnLogout);
            btnLogout.setBounds(810, 5, 80, 50);

            //---- separator2 ----
            separator2.setOrientation(SwingConstants.VERTICAL);
            header.add(separator2);
            separator2.setBounds(800, 5, 15, 50);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < header.getComponentCount(); i++) {
                    Rectangle bounds = header.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = header.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                header.setMinimumSize(preferredSize);
                header.setPreferredSize(preferredSize);
            }
        }
        add(header);
        header.setBounds(5, 5, 890, 60);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Thanh Dao Ngoc
    private JPanel panel2;
    private JPanel panel3;
    private JButton button1;
    private JButton button2;
    private JPanel header;
    private JLabel lbName;
    private JLabel lbRole;
    private JButton btnLogout;
    private JSeparator separator2;


    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
