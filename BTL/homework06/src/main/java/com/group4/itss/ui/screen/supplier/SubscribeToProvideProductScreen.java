/*
 * Created by JFormDesigner on Mon Dec 02 22:04:26 ICT 2019
 */

package com.group4.itss.ui.screen.supplier;

import com.group4.itss.common.AppState;
import com.group4.itss.controller.ProvidedMerchandiseController;
import com.group4.itss.controller.SaleOrderController;
import com.group4.itss.controller.model.SaleOrderLineResponse;
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
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tran Ngoc
 */
@Component("subscribe-product")
public class SubscribeToProvideProductScreen extends BaseScreen {

    private final ProvidedMerchandiseController providedMerchandiseController;

    private final SaleOrderController saleOrderController;

    private Supplier supplier;

    private int[] columnWidth = {50,140,120,90,120,120,120,80};

    public SubscribeToProvideProductScreen(ProvidedMerchandiseController providedMerchandiseController, SaleOrderController saleOrderController) {
        this.providedMerchandiseController = providedMerchandiseController;
        this.saleOrderController = saleOrderController;
        initComponents();
        panelBreadcrumb.add(Breadcrumb.create(Arrays.asList("Home", "Subscribe")));
    }


    public void onLoadData() {
        supplier = (Supplier) appState.getAccount();
        labelName.setText(supplier.getName());
        labelRole.setText(supplier.getRole());
        List<SaleOrderLineResponse> saleOrders = saleOrderController.getSaleOrderResponseByOrderCode(appState.getCurrentSaleOrder().getCode());
        val supplierCode = supplier.getCode();
        val data = getLinesNotProvided(saleOrders, supplierCode);
        String[] columnNames = {"No.", "Merchandise code", "Quantity ordered", "Unit", "Quantity provided", "Price($)", "Status","Actions"};
        int columnCount = columnNames.length;

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

        data.forEach(order->{
            model.addRow(new Object[]{model.getRowCount()+1,order.getMerchandiseCode(), order.getQuantity(),order.getUnit(), "", "","","Add"});
        });
        table1.setModel(model);
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
    }

    private List<ProvidedMerchandise> createSubscribeList(){
        supplier = (Supplier) appState.getAccount();
        String code = supplier.getCode();
        List<ProvidedMerchandise> merchandiseList = new ArrayList<>();
        int rowCount = table1.getRowCount();
        for(int i = 0 ;i < rowCount; i++) {
            if (StringUtils.equals(table1.getValueAt(i, 6).toString(), "Subscribed")) {
                if (checkInput(table1, i, 4, 5) == 1) {
                    ProvidedMerchandise provide = new ProvidedMerchandise();
                    provide.setMerchandiseCode((String) table1.getValueAt(i, 1));
                    provide.setUnit((String) table1.getValueAt(i, 3));
                    provide.setQuantity(Integer.parseInt(table1.getValueAt(i, 4).toString()));
                    provide.setPrice(Double.parseDouble(table1.getValueAt(i, 5).toString()));
                    provide.setStatus(ProvidedMerchandiseStatus.processing);
                    provide.setCreatedDate(LocalDate.now());
                    provide.setSupplierCode(code);
                    provide.setOrderCode(appState.getCurrentSaleOrder().getCode());
                    merchandiseList.add(provide);
                }
            }
        }
        return merchandiseList;
    }

    private List<SaleOrderLineResponse> getLinesNotProvided(List<SaleOrderLineResponse> saleOrderLines, String supplierCode){
        val merchandise = providedMerchandiseController.findByOrderCodeAndSupplierCode(appState.getCurrentSaleOrder().getCode(),supplierCode);
        return saleOrderLines.stream().filter(line->!contain(merchandise, line)).collect(Collectors.toList());
    }

    private boolean contain(List<ProvidedMerchandise> providedMerchandises, SaleOrderLineResponse orderLine){
        if(providedMerchandises!=null){
            for (ProvidedMerchandise merchandise :providedMerchandises) {
                if(StringUtils.equals(merchandise.getMerchandiseCode(), orderLine.getMerchandiseCode())){
                    return true;
                }
            }
        }
        return false;
    }

    private void btCancelMouseClicked(MouseEvent e) {
       if(PopUp.render(PopUp.PopupTypes.CONFIRM, "Cancel this action", "Cancel") == 2){
           navigator.goToHome();
       }
    }

    private void btSubmitMouseClicked(MouseEvent e) {
        int confirm = PopUp.render(PopUp.PopupTypes.CONFIRM, "Submit selected merchandise", "Submit");
        if(confirm==2){
            providedMerchandiseController.saveAll(createSubscribeList());
            PopUp.render(PopUp.PopupTypes.SUCCESS,"Successful","Subscribe Merchandise");
            navigator.goToHome();
        }
    }

    private void btLogoutMouseClicked(MouseEvent e) {
        navigator.onLogout();
    }
    private int checkInput(JTable table, int row, int quantityCol, int priceCol){
        if(NumberUtils.isParsable(table.getValueAt(row, quantityCol).toString()) && NumberUtils.isParsable(table.getValueAt(row,priceCol).toString())) {
            if(NumberUtils.toInt(table.getValueAt(row, quantityCol).toString()) >0
                    && NumberUtils.toDouble(table.getValueAt(row,priceCol).toString()) > 0.0) {
                return 1;
            }
            else {
                PopUp.render(PopUp.PopupTypes.ERROR,"Quantity and Price must be greater than zero", "Error");
            }
        }
        else if(StringUtils.isBlank(table.getValueAt(row, quantityCol).toString()) || StringUtils.isBlank(table.getValueAt(row,priceCol).toString())){
            PopUp.render(PopUp.PopupTypes.ERROR,"Quantity and Price must not be blank","Error");
        }
        else{
            PopUp.render(PopUp.PopupTypes.ERROR, "Invalid number format", "Error");
        }
        return 0;
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
        btnSubmit = new JButton();
        btnCancel = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
        . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border . TitledBorder
        . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg", java .
        awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
        ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "\u0062or\u0064er" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
        ;
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
            labelRole.setText("Supplier Employee");
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
        panelBreadcrumb.setBounds(6, 65, 894, 31);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(30, 145, 840, 240);

        //---- btnSubmit ----
        btnSubmit.setText("Submit");
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btSubmitMouseClicked(e);
            }
        });
        add(btnSubmit);
        btnSubmit.setBounds(790, 400, 80, 30);

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
    private JButton btnSubmit;
    private JButton btnCancel;

    @Override
    public void display() {
        onLoadData();
        Utils.customTable(table1, columnWidth);
        ButtonColumn buttonColumn = new ButtonColumn(table1, 7){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                int columnCount = table1.getColumnCount();
                int row = table1.getSelectedRow();
                if(checkInput(table1, row, 4,5)==1){
                    table1.setValueAt("Subscribed", row,columnCount-2);
                }
            }
        };
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
