/*
 * Created by JFormDesigner on Tue Dec 10 18:27:25 ICT 2019
 */

package com.group4.itss.ui.screen.stock;

import com.group4.itss.common.Constants;
import com.group4.itss.controller.SupplierOrderController;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.SupplierOrder;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.ButtonRenderer;
import com.group4.itss.ui.component.PopUp;
import com.group4.itss.ui.screen.stock.supplierordertable.ActionButtonEditor;
import com.group4.itss.ui.screen.stock.supplierordertable.SupplierOrderTableModel;
import com.group4.itss.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author shyn
 */
@Component("stock-home-screen")
public class StockHomeScreen extends BaseScreen {

    @Autowired
    private SupplierOrderController supplierOrderController;

    private List<SupplierOrder> supplierOrderList = new ArrayList<>();

    private int[] columnWidth = {50, 200, 200, 290, 100};

    public StockHomeScreen() {
        initComponents();
        pnBreadCrumb.add(Breadcrumb.create(Collections.singletonList("Home")));
    }

    private void loadData() {
        try {
            supplierOrderList = supplierOrderController.getAllOrder();
            supplierOrderList.sort(Comparator.comparingLong(o -> -o.getCreatedDate().getTime()));
            appState.setCurSupplierOrderList(supplierOrderList);
        } catch (Exception e) {
            PopUp.render(PopUp.PopupTypes.ERROR, Constants.MESSAGES.ERROR_MSG, "Error");
        }
    }

    private void onBtnLogoutMouseClicked(MouseEvent e) {
        navigator.onLogout();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - shyn
        header = new JPanel();
        lbName = new JLabel();
        lbRole = new JLabel();
        btnLogout = new JButton();
        separator2 = new JSeparator();
        pnBreadCrumb = new JPanel();
        separator1 = new JSeparator();
        scrpTableList = new JScrollPane();
        tbSupplierOrderList = new JTable();

        //======== this ========
        setLayout(null);

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
            lbRole.setText("Stock Employee");
            lbRole.setHorizontalAlignment(SwingConstants.RIGHT);
            header.add(lbRole);
            lbRole.setBounds(670, 30, 120, 25);

            //---- btnLogout ----
            btnLogout.setText("Logout");
            btnLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onBtnLogoutMouseClicked(e);
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

        //======== pnBreadCrumb ========
        {
            pnBreadCrumb.setLayout(new GridLayout());
        }
        add(pnBreadCrumb);
        pnBreadCrumb.setBounds(0, 70, 900, 40);
        add(separator1);
        separator1.setBounds(30, 170, 840, 12);

        //======== scrpTableList ========
        {
            scrpTableList.setViewportView(tbSupplierOrderList);
        }
        add(scrpTableList);
        scrpTableList.setBounds(30, 200, 840, 260);

        setPreferredSize(new Dimension(900, 500));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - shyn
    private JPanel header;
    private JLabel lbName;
    private JLabel lbRole;
    private JButton btnLogout;
    private JSeparator separator2;
    private JPanel pnBreadCrumb;
    private JSeparator separator1;
    private JScrollPane scrpTableList;
    private JTable tbSupplierOrderList;

    @Override
    public void display() {
        loadData();
        tbSupplierOrderList.setModel(new SupplierOrderTableModel(supplierOrderList));
        Utils.customTable(tbSupplierOrderList, columnWidth);
        tbSupplierOrderList.getColumn("Action").setCellRenderer(new ButtonRenderer());
        tbSupplierOrderList.getColumn("Action").setCellEditor(new ActionButtonEditor(new JCheckBox(), appState, navigator));
        Employee employee = (Employee) appState.getAccount();
        lbName.setText(employee.getName());
        lbRole.setText(employee.getRole());
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
