package com.group4.itss.ui.screen.supplierorder;

import java.awt.event.*;
import javax.swing.border.*;
import com.group4.itss.common.AppState;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.SupplierOrder;
import com.group4.itss.entity.model.SupplierOrderLine;
import com.group4.itss.repository.SupplierOrderLineRepository;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

@Component("detail-supplier-order")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DetailSupplierOrder extends BaseScreen {
    private Navigator navigator;
    private AppState appState;
    private SupplierOrderLineRepository supplierOrderLineRepository;
    public DetailSupplierOrder(Navigator navigator, AppState appState, SupplierOrderLineRepository supplierOrderLineRepository) {
        this.navigator = navigator;
        this.appState = appState;
        this.supplierOrderLineRepository = supplierOrderLineRepository;
        initComponents();
        customize();
        loadData();
    }

    private void customize(){
        panel2.add(Breadcrumb.create(Arrays.asList("Home","View Supplier Order", appState.getCurSupplierOrder().getCode())));
    }

    private void loadData(){
        List<SupplierOrderLine> supplierOrderLineList = new ArrayList<>();
        SupplierOrder curSupplierOrder = appState.getCurSupplierOrder();
        if(curSupplierOrder!=null)
            supplierOrderLineList = supplierOrderLineRepository.findByOrderCode(curSupplierOrder.getCode());
        DetailSupplierOrderModel detailSupplierOrderModel = new DetailSupplierOrderModel(supplierOrderLineList);
        table1.setModel(detailSupplierOrderModel);
    }

    private void backHandler(MouseEvent e) {
        navigator.navigate("supplier-order-list-screen");
    }

    private void btLogoutMouseClicked(MouseEvent e) {
        navigator.onLogout();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Thanh Tung
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button2 = new JButton();
        header = new JPanel();
        lbName = new JLabel();
        lbRole = new JLabel();
        btnLogout = new JButton();
        separator2 = new JSeparator();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
        border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER
        , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font
        .BOLD ,12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
        new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order"
        .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout());
        }
        add(panel2);
        panel2.setBounds(6, 75, 804, 52);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setGridColor(Color.black);
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(6, 137, 884, 283);

        //---- button2 ----
        button2.setText("Back");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backHandler(e);
            }
        });
        add(button2);
        button2.setBounds(790, 430, 100, 43);

        //======== header ========
        {
            header.setBorder(LineBorder.createBlackLineBorder());
            header.setLayout(null);

            //---- lbName ----
            lbName.setText("ORDER");
            lbName.setHorizontalAlignment(SwingConstants.RIGHT);
            header.add(lbName);
            lbName.setBounds(440, 5, 350, 25);

            //---- lbRole ----
            lbRole.setText("ORDER EMPLOYEE");
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
    // Generated using JFormDesigner Evaluation license - Thanh Tung
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button2;
    private JPanel header;
    private JLabel lbName;
    private JLabel lbRole;
    private JButton btnLogout;
    private JSeparator separator2;

    @Override
    public void display() {
        Employee employee = (Employee) appState.getAccount();
        lbName.setText(employee.getName());
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
