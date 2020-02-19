/*
 * Created by JFormDesigner on Wed Dec 11 01:23:28 ICT 2019
 */

package com.group4.itss.ui.screen.stock;

import com.group4.itss.common.Constants;
import com.group4.itss.controller.SupplierOrderLineController;
import com.group4.itss.entity.model.SupplierOrderLine;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.PopUp;
import com.group4.itss.ui.screen.stock.supplierordertable.SupplierOrderLineTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shyn
 */
@Component("stock-view-supplier-order-popup-screen")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ViewSupplierOrderPopupScreen extends BaseScreen {

    @Autowired
    private SupplierOrderLineController supplierOrderLineController;

    private int[] columnWidth = {50, 240, 100, 240, 130};

    private List<SupplierOrderLine> supplierOrderLineList = new ArrayList<>();

    public ViewSupplierOrderPopupScreen() {
        initComponents();
    }

    private void customTable() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < columnWidth.length; i++) {
            TableColumn column = tbOrderLine.getColumnModel().getColumn(i);
            if (i != columnWidth.length -1)
                column.setCellRenderer(centerRenderer);
            column.setWidth(columnWidth[i]);
            column.setPreferredWidth(columnWidth[i]);
            column.setMinWidth(columnWidth[i]);
            column.setMaxWidth(columnWidth[i]);
            column.setResizable(false);
        }

        tbOrderLine.setGridColor(Color.BLACK);
    }

    private void loadData() {
        try {
            supplierOrderLineList = supplierOrderLineController.getAllLinesByOrderCode(appState.getCurSupplierOrder().getCode());
        } catch (Exception e) {
            PopUp.render(PopUp.PopupTypes.ERROR, Constants.MESSAGES.ERROR_MSG, "Error");
        }
    }

    private void onBtnSubmitActionPerformed(ActionEvent event) {
        int opt = PopUp.render(PopUp.PopupTypes.CONFIRM, Constants.MESSAGES.SUBMIT_CONFIRM_MSG, "Submit");
        if (opt != 2) return;
        try {
            List<SupplierOrderLine> newData = ((SupplierOrderLineTableModel) tbOrderLine.getModel()).getData();
            supplierOrderLineController.updateOrderLines(newData);
            PopUp.render(PopUp.PopupTypes.SUCCESS, Constants.MESSAGES.UPDATE_ORDER_SUCCESS_MSG, "Success");
        } catch (Exception e) {
            PopUp.render(PopUp.PopupTypes.ERROR, Constants.MESSAGES.ERROR_MSG, "Error");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - shyn
        lbTitle = new JLabel();
        scrpTable = new JScrollPane();
        tbOrderLine = new JTable();
        btnSubmit = new JButton();

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
            scrpTable.setViewportView(tbOrderLine);
        }
        add(scrpTable);
        scrpTable.setBounds(20, 60, 760, 310);

        //---- btnSubmit ----
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(this::onBtnSubmitActionPerformed);
        add(btnSubmit);
        btnSubmit.setBounds(660, 20, 120, 25);

        setPreferredSize(new Dimension(800, 400));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - shyn
    private JLabel lbTitle;
    private JScrollPane scrpTable;
    private JTable tbOrderLine;
    private JButton btnSubmit;

    @Override
    public void display() {
        lbTitle.setText("Order Code: " + appState.getCurSupplierOrder().getCode());
        loadData();
        tbOrderLine.setModel(new SupplierOrderLineTableModel(supplierOrderLineList));
        customTable();
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
