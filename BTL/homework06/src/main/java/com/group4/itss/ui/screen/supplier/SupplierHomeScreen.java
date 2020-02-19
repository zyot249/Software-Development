package com.group4.itss.ui.screen.supplier;

import com.group4.itss.common.Constants;
import com.group4.itss.controller.SaleOrderController;
import com.group4.itss.controller.SaleOrderLineController;
import com.group4.itss.entity.model.SaleOrder;
import com.group4.itss.entity.model.Supplier;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.ButtonRenderer;
import com.group4.itss.ui.component.PopUp;
import com.group4.itss.ui.screen.supplier.saleordertable.ActionButtonEditor;
import com.group4.itss.ui.screen.supplier.saleordertable.SaleOrderTableModel;
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
@Component("supplier-home-screen")
public class SupplierHomeScreen extends BaseScreen {

    @Autowired
    private SaleOrderController saleOrderController;

    @Autowired
    private SaleOrderLineController saleOrderLineController;

    private List<SaleOrder> saleOrderList = new ArrayList<>();

    private int[] columnWidth = {50, 200, 200, 290, 100};

    public SupplierHomeScreen() {
        initComponents();
        pnBreadCrumb.add(Breadcrumb.create(Collections.singletonList("Home")));
    }

    private void loadData() {
        try {
            saleOrderList = saleOrderController.getSaleOrdersByOrderCodeList(saleOrderLineController.getUncompletedOrderCode());
            Supplier supplier = (Supplier) appState.getAccount();
            for (int i = 0; i < saleOrderList.size(); i++) {
                if(saleOrderLineController.isSubscribedOrder(saleOrderList.get(i).getCode(),supplier.getCode())) {
                    saleOrderList.remove(i);
                }
            }
            saleOrderList.sort(Comparator.comparingLong(o -> -o.getCreatedDate().getTime()));
            appState.setSaleOrderList(saleOrderList);
            tbOrderList.setModel(new SaleOrderTableModel(saleOrderList));
        } catch (Exception e) {
            PopUp.render(PopUp.PopupTypes.ERROR, Constants.MESSAGES.ERROR_MSG, "Error");
        }
    }

    private void onBtnEditMouseClicked(MouseEvent e) {
        navigator.navigate("update-supplier-info-screen");
    }

    private void onBtnLogoutMouseClicked(MouseEvent e) {
        navigator.onLogout();
    }

    private void onBtnUpdatePPMouseClicked(MouseEvent e) {
        navigator.navigate("update-product");
    }

    private void onBtnSubscribePPMouseClicked(MouseEvent e) {
        SaleOrderTableModel model = (SaleOrderTableModel) tbOrderList.getModel();
        if (model.getData().get(tbOrderList.getSelectedRow()) == null) {
            PopUp.render(PopUp.PopupTypes.ERROR, Constants.MESSAGES.NO_SELECTED_ROW_MSG, "Warning");
            return;
        }
        appState.setCurrentSaleOrder(model.getData().get(tbOrderList.getSelectedRow()));
        Supplier supplier = (Supplier) appState.getAccount();
        if(saleOrderLineController.isSubscribedOrder(appState.getCurrentSaleOrder().getCode(),supplier.getCode())){
            PopUp.render(PopUp.PopupTypes.SUCCESS,Constants.MESSAGES.SUBSCRIBED_MERCHANDISE_MSG,"Notification");
        }else {
            navigator.navigate("subscribe-product");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - shyn
        header = new JPanel();
        lbName = new JLabel();
        lbRole = new JLabel();
        btnLogout = new JButton();
        btnEdit = new JButton();
        separator2 = new JSeparator();
        pnBreadCrumb = new JPanel();
        btnSubscribePP = new JButton();
        btnUpdatePP = new JButton();
        separator1 = new JSeparator();
        scrlpTable = new JScrollPane();
        tbOrderList = new JTable();

        //======== this ========
        setLayout(null);

        //======== header ========
        {
            header.setBorder(LineBorder.createBlackLineBorder());
            header.setLayout(null);

            //---- lbName ----
            lbName.setText("Name");
            lbName.setHorizontalAlignment(SwingConstants.RIGHT);
            header.add(lbName);
            lbName.setBounds(440, 5, 350, 25);

            //---- lbRole ----
            lbRole.setText("Supplier");
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
            btnLogout.setBounds(810, 5, 80, 25);

            //---- btnEdit ----
            btnEdit.setText("Edit");
            btnEdit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onBtnEditMouseClicked(e);
                }
            });
            header.add(btnEdit);
            btnEdit.setBounds(810, 30, 80, 25);

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
        pnBreadCrumb.setBounds(0, 75, 900, 40);

        //---- btnSubscribePP ----
        btnSubscribePP.setText("Subscribe providing product");
        btnSubscribePP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onBtnSubscribePPMouseClicked(e);
            }
        });
        add(btnSubscribePP);
        btnSubscribePP.setBounds(410, 125, 225, 40);

        //---- btnUpdatePP ----
        btnUpdatePP.setText("Update providing product");
        btnUpdatePP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onBtnUpdatePPMouseClicked(e);
            }
        });
        add(btnUpdatePP);
        btnUpdatePP.setBounds(645, 125, 225, 40);
        add(separator1);
        separator1.setBounds(30, 170, 840, separator1.getPreferredSize().height);

        //======== scrlpTable ========
        {
            scrlpTable.setViewportView(tbOrderList);
        }
        add(scrlpTable);
        scrlpTable.setBounds(30, 200, 840, 260);

        setPreferredSize(new Dimension(900, 500));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - shyn
    private JPanel header;
    private JLabel lbName;
    private JLabel lbRole;
    private JButton btnLogout;
    private JButton btnEdit;
    private JSeparator separator2;
    private JPanel pnBreadCrumb;
    private JButton btnSubscribePP;
    private JButton btnUpdatePP;
    private JSeparator separator1;
    private JScrollPane scrlpTable;
    private JTable tbOrderList;

    @Override
    public void display() {
        Supplier supplier = (Supplier) appState.getAccount();
        lbName.setText(supplier.getName());
        lbRole.setText(supplier.getRole());
        loadData();
        Utils.customTable(tbOrderList, columnWidth);
        tbOrderList.getColumn("Action").setCellRenderer(new ButtonRenderer());
        tbOrderList.getColumn("Action").setCellEditor(new ActionButtonEditor(new JCheckBox(), appState, navigator));
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
