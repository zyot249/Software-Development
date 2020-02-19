/*
 * Created by JFormDesigner on Sun Dec 08 16:18:15 ICT 2019
 */

package com.group4.itss.ui.screen.saleemployee;

import com.group4.itss.common.AppState;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

@Component("sale-employee-home")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SaleEmployeeHome extends BaseScreen {
    private Navigator navigator;
    private AppState appState;

    public SaleEmployeeHome(Navigator navigator, AppState appState) {
        this.navigator = navigator;
        this.appState = appState;
        initComponents();
        customize();
    }

    private void customize(){
        panel2.add(Breadcrumb.create(Arrays.asList("Home")));
    }

    private void logOutHandler(MouseEvent e) {
        navigator.onLogout();
    }

    private void createSaleOrder(MouseEvent e) { navigator.navigate("create-sale-order"); }

    private void viewSaleOrder(MouseEvent e) {
        navigator.navigate("view-sale-order");
    }

    private void createDataMerchandise(MouseEvent e) {
        navigator.navigate("create-merchandise-screen");
    }

    private void viewDataMerchandise(MouseEvent e) {
        navigator.navigate("update-data-merchandise");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Thanh Tung
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        button3 = new JButton();
        separator1 = new JSeparator();
        panel2 = new JPanel();
        panel3 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button4 = new JButton();
        button5 = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(900, 500));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
        . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder
        . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .
        awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
        ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
        ;
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(new LineBorder(Color.black, 1, true));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("Thanh Tung Vu");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label1);
            label1.setBounds(420, 5, 350, 25);

            //---- label2 ----
            label2.setText("Sale Employee");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label2);
            label2.setBounds(650, 30, 120, 25);

            //---- button3 ----
            button3.setText("Log out");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    logOutHandler(e);
                }
            });
            panel1.add(button3);
            button3.setBounds(780, 5, 100, 50);

            //---- separator1 ----
            separator1.setOrientation(SwingConstants.VERTICAL);
            panel1.add(separator1);
            separator1.setBounds(775, 5, 15, 50);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        add(panel1);
        panel1.setBounds(5, 5, 890, 60);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(1, 2));
        }
        add(panel2);
        panel2.setBounds(5, 75, 600, 45);

        //======== panel3 ========
        {

            //---- button1 ----
            button1.setText("Create Sale Order");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    createSaleOrder(e);
                }
            });

            //---- button2 ----
            button2.setText("Create Data Merchandise");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    createDataMerchandise(e);
                }
            });

            //---- button4 ----
            button4.setText("View Sale Order");
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    viewSaleOrder(e);
                }
            });

            //---- button5 ----
            button5.setText("Update Data Merchandise");
            button5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    viewDataMerchandise(e);
                }
            });

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(button1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(button4, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addGroup(panel3Layout.createParallelGroup()
                            .addComponent(button5, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button5, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button4, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
            );
        }
        add(panel3);
        panel3.setBounds(new Rectangle(new Point(6, 115), panel3.getPreferredSize()));

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
    // Generated using JFormDesigner Evaluation license - Thanh Tung
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JButton button3;
    private JSeparator separator1;
    private JPanel panel2;
    private JPanel panel3;
    private JButton button1;
    private JButton button2;
    private JButton button4;
    private JButton button5;

    @Override
    public void display() {
        Employee employee = (Employee) appState.getAccount();
        label1.setText(employee.getName());
        label2.setText(employee.getRole());
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
