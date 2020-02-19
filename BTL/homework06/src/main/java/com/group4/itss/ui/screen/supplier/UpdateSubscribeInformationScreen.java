/*
 * Created by JFormDesigner on Tue Dec 03 23:51:22 ICT 2019
 */

package com.group4.itss.ui.screen.supplier;

import com.group4.itss.common.AppState;
import com.group4.itss.controller.ProvidedMerchandiseController;
import com.group4.itss.entity.enumeration.ProvidedMerchandiseStatus;
import com.group4.itss.entity.model.ProvidedMerchandise;
import com.group4.itss.entity.model.Supplier;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.ButtonColumn;
import com.group4.itss.ui.component.PopUp;
import com.group4.itss.util.Utils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author Tran Ngoc
 */
@Component("update-product")
public class UpdateSubscribeInformationScreen extends BaseScreen {
    private final ProvidedMerchandiseController providedMerchandiseController;
    private Supplier supplier;
    private List<ProvidedMerchandise> data;
    private int[] columnWidth = {50,140,120,90,120,120,120,80};
    public UpdateSubscribeInformationScreen(ProvidedMerchandiseController providedMerchandiseController) {
        this.providedMerchandiseController = providedMerchandiseController;
        initComponents();
        panelBreadcrumb.add(Breadcrumb.create(Arrays.asList("Home", "Update")));
    }

    public void onLoadData() {
        supplier = (Supplier) appState.getAccount();
        labelName.setText(supplier.getName());
        labelRole.setText(supplier.getRole());
        data = providedMerchandiseController.findBySupplierCodeAndStatus(supplier.getCode(), ProvidedMerchandiseStatus.processing);
        String[] columnNames = {"No.", "Merchandise code","Order Code" ,"Unit","Quantity","Price($)","Status","Actions"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0)
        {
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5 || column == 7;
            }
        };
        data.forEach(merchandise -> {
            model.addRow(new Object[]{model.getRowCount()+1,merchandise.getMerchandiseCode(), merchandise.getOrderCode(),
                    merchandise.getUnit(),merchandise.getQuantity(), merchandise.getPrice(), merchandise.getStatus(),"Delete"});
        });
        table1.setModel(model);
    }

    private int updateData(){
        for(int i = 0; i < data.size(); i++){
            if(StringUtils.equals("Closed", table1.getValueAt(i, 6).toString())){
                data.get(i).setStatus(ProvidedMerchandiseStatus.closed);
            }
            else {
                if(checkInput(table1,i, 4,5)==0) return 0;
            }
        }
        return 1;
    }

    private void btSaveMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(PopUp.render(PopUp.PopupTypes.CONFIRM, "Save all change", "Save") == 2){
            if(updateData()==1) {
                providedMerchandiseController.saveAll(data);
                PopUp.render(PopUp.PopupTypes.SUCCESS,"Successful","Update provided merchandise");
                navigator.goToHome();
            }
        }
    }

    private void btCancelMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(PopUp.render(PopUp.PopupTypes.CONFIRM, "Cancel this action", "Cancel") == 2){
            navigator.goToHome();
        }
    }

    private void btLogoutMouseClicked(MouseEvent e) {
        // TODO add your code here
        navigator.onLogout();
    }

    private int checkInput(JTable table, int row, int quantityCol, int priceCol){
        if(NumberUtils.isParsable(table.getValueAt(row, quantityCol).toString()) && NumberUtils.isParsable(table.getValueAt(row,priceCol).toString())) {
            if(NumberUtils.toInt(table.getValueAt(row, quantityCol).toString()) >0
                    && NumberUtils.toDouble(table.getValueAt(row,priceCol).toString()) > 0.0) {
                data.get(row).setQuantity(Integer.parseInt(table.getValueAt(row, quantityCol).toString()));
                data.get(row).setPrice(Double.parseDouble(table.getValueAt(row, priceCol).toString()));
                return 1;
            }
            else{
                PopUp.render(PopUp.PopupTypes.ERROR,data.get(row).getMerchandiseCode()+": Quantity and Price must be greater than zero","Error");
                return 0;
            }
        }
        else if(StringUtils.isBlank(table.getValueAt(row, quantityCol).toString()) || StringUtils.isBlank(table.getValueAt(row,priceCol).toString())){
            PopUp.render(PopUp.PopupTypes.ERROR,data.get(row).getMerchandiseCode()+": Quantity and Price must not be blank","Error");
            return 0;
        }
        else{
            PopUp.render(PopUp.PopupTypes.ERROR,data.get(row).getMerchandiseCode()+": Invalid number format","Error");
            return 0;
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Tran Ngoc
        panelHeader = new JPanel();
        labelName = new JLabel();
        labelRole = new JLabel();
        btnLogout = new JButton();
        separator1 = new JSeparator();
        panelBreadcrumb = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        btnSave = new JButton();
        btnCancel = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
        setLayout(null);

        //======== panelHeader ========
        {
            panelHeader.setBorder(LineBorder.createBlackLineBorder());
            panelHeader.setLayout(null);

            //---- labelName ----
            labelName.setText("Tran Bich Ngoc");
            labelName.setHorizontalAlignment(SwingConstants.RIGHT);
            panelHeader.add(labelName);
            labelName.setBounds(440, 5, 350, 25);

            //---- labelRole ----
            labelRole.setText("    Supplier Employee");
            labelRole.setHorizontalAlignment(SwingConstants.RIGHT);
            panelHeader.add(labelRole);
            labelRole.setBounds(670, 30, 120, 25);

            //---- btnLogout ----
            btnLogout.setText("Logout");
            btnLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btLogoutMouseClicked(e);
                }
            });
            panelHeader.add(btnLogout);
            btnLogout.setBounds(810, 5, 80, 50);

            //---- separator1 ----
            separator1.setOrientation(SwingConstants.VERTICAL);
            panelHeader.add(separator1);
            separator1.setBounds(800, 5, 15, 50);
        }
        add(panelHeader);
        panelHeader.setBounds(5, 5, 890, 60);

        //======== panelBreadcrumb ========
        {
            panelBreadcrumb.setLayout(new GridLayout());
        }
        add(panelBreadcrumb);
        panelBreadcrumb.setBounds(0, 70, 894, 38);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(30, 145, 840, 240);

        //---- btnSave ----
        btnSave.setText("Save");
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btSaveMouseClicked(e);
            }
        });
        add(btnSave);
        btnSave.setBounds(790, 400, 80, 30);

        //---- btnCancel ----
        btnCancel.setText("Cancel");
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btCancelMouseClicked(e);
            }
        });
        add(btnCancel);
        btnCancel.setBounds(675, 400, 80, 30);

        setPreferredSize(new Dimension(900, 500));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Tran Ngoc
    private JPanel panelHeader;
    private JLabel labelName;
    private JLabel labelRole;
    private JButton btnLogout;
    private JSeparator separator1;
    private JPanel panelBreadcrumb;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton btnSave;
    private JButton btnCancel;

    @Override
    public void display() {
        onLoadData();
        Utils.customTable(table1,columnWidth);
        ButtonColumn buttonColumn = new ButtonColumn(table1, 7){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                int row = table1.getSelectedRow();
                table1.setValueAt("Closed",row, 6);
            }
        };
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
