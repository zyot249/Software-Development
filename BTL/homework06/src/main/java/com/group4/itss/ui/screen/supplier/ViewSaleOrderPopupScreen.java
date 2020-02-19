/*
 * Created by JFormDesigner on Tue Dec 10 23:32:19 ICT 2019
 */

package com.group4.itss.ui.screen.supplier;

import com.group4.itss.controller.SaleOrderLineController;
import com.group4.itss.entity.model.SaleOrderLine;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.screen.supplier.saleordertable.SaleOrderLineTableModel;
import com.group4.itss.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shyn
 */
@Component("supplier-view-sale-order-popup-screen")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ViewSaleOrderPopupScreen extends BaseScreen {

    @Autowired
    private SaleOrderLineController saleOrderLineController;

    private int[] columnWidth = {50, 240, 100, 240, 130};

    private List<SaleOrderLine> saleOrderLineList = new ArrayList<>();

    public ViewSaleOrderPopupScreen() {
        initComponents();
    }

    private void loadData() {
        saleOrderLineList = saleOrderLineController.getLinesByOrderCode(appState.getCurrentSaleOrder().getCode());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - shyn
        lbTitle = new JLabel();
        scrpTable = new JScrollPane();
        tbSaleOrderLine = new JTable();

        //======== this ========
        setLayout(null);

        //---- lbTitle ----
        lbTitle.setText("Order Code: ORD0001");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
        lbTitle.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(lbTitle);
        lbTitle.setBounds(200, 5, 400, 40);

        //======== scrpTable ========
        {
            scrpTable.setViewportView(tbSaleOrderLine);
        }
        add(scrpTable);
        scrpTable.setBounds(20, 60, 760, 310);

        setPreferredSize(new Dimension(800, 400));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - shyn
    private JLabel lbTitle;
    private JScrollPane scrpTable;
    private JTable tbSaleOrderLine;

    @Override
    public void display() {
        lbTitle.setText("Order Code: " + appState.getCurrentSaleOrder().getCode());
        loadData();
        tbSaleOrderLine.setModel(new SaleOrderLineTableModel(saleOrderLineList));
        Utils.customTable(tbSaleOrderLine, columnWidth);
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
