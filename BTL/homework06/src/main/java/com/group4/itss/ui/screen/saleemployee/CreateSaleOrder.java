package com.group4.itss.ui.screen.saleemployee;

import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.*;

import com.group4.itss.common.AppState;
import com.group4.itss.controller.MerchandiseController;
import com.group4.itss.controller.SaleOrderController;
import com.group4.itss.entity.enumeration.SaleOrderLineStatus;
import com.group4.itss.entity.enumeration.SaleOrderStatus;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.Merchandise;
import com.group4.itss.entity.model.SaleOrder;
import com.group4.itss.entity.model.SaleOrderLine;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.*;
import com.group4.itss.ui.screen.saleemployee.tablemodel.MerchandiseCellEditor;
import com.group4.itss.ui.screen.saleemployee.tablemodel.SaleOrderTableModel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

@Component("create-sale-order")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateSaleOrder extends BaseScreen {
    private Navigator navigator;
    private AppState appState;
    private SaleOrderController saleOrderController;
    private MerchandiseController merchandiseController;

    private CreateSaleOrder(Navigator navigator,
                            AppState appState,
                            MerchandiseController merchandiseController,
                            SaleOrderController saleOrderController) {
        this.appState = appState;
        this.navigator = navigator;
        this.merchandiseController = merchandiseController;
        this.saleOrderController = saleOrderController;
        initComponents();
        customize();
    }


    private void  customize() {
        List<Merchandise> merchandiseList = merchandiseController.getAllMerchandise();
        table1.setModel(new SaleOrderTableModel());
        table1.getColumn("Merchandise Name").setCellEditor(new MerchandiseCellEditor(merchandiseList));
        panel2.add(Breadcrumb.create(Arrays.asList("Home", "Create sale order")));
    }

    private void addRowHandler(MouseEvent e) {
        SaleOrderTableModel model = (SaleOrderTableModel) table1.getModel();
        model.addRow(new SaleOrderLine());
    }

    private void submitHandler(MouseEvent event) {
        SaleOrder saleOrder = createSaleOrder();
        if(!saleOrderController.checkValidSaleOrder(((SaleOrderTableModel) table1.getModel()).getSaleOrderLineList())){
            PopUp.render(PopUp.PopupTypes.ERROR,"Please fulfill all fields!","Warning!");
            return;
        }

        if(PopUp.render(PopUp.PopupTypes.CONFIRM, "Are you sure to submit?", "Submit Sale Order") == 2) {
            List<SaleOrderLine> saleOrderLineList = createSaleOrderLineList(saleOrder.getCode());
            if(saleOrderController.updateNewSaleOrder(saleOrder, saleOrderLineList)) {
                onResetForm();
                navigator.goToHome();
            }
        }
    }

    private SaleOrder createSaleOrder() {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setCreatedDate(Calendar.getInstance().getTime());
        saleOrder.setCreator(appState.getAccount().getId().toString());
        saleOrder.setCode(saleOrderController.createSaleOrderCode());
        saleOrder.setStatus(SaleOrderStatus.PENDING);
        return saleOrder;
    }

    private List<SaleOrderLine> createSaleOrderLineList(String orderCode) {
        SaleOrderTableModel model = (SaleOrderTableModel) table1.getModel();
        List<SaleOrderLine> saleOrderLineList = model.getSaleOrderLineList();

        saleOrderLineList.forEach(saleOrderLine -> {
            saleOrderLine.setOrderCode(orderCode);
            saleOrderLine.setStatus(SaleOrderLineStatus.PENDING);
        });
        return saleOrderLineList;
    }

    private void onResetForm() {
        SaleOrderTableModel model = (SaleOrderTableModel) table1.getModel();
        model.getSaleOrderLineList().clear();
        model.getMerchandiseList().clear();
        model.fireTableDataChanged();
    }

    private void cancelHandler(MouseEvent event){
        if(PopUp.render(PopUp.PopupTypes.CONFIRM,"Are you sure to cancel?","Cancel Sale Order") == 2) {
            onResetForm();
            navigator.navigate("sale-employee-home");
        }
    }

    private void onLogout(MouseEvent event) {
        navigator.onLogout();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Thanh Tung
        panel1 = new JPanel();
        saleNameLabel = new JLabel();
        ruleLabel = new JLabel();
        logoutBtn = new JButton();
        separator1 = new JSeparator();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        cancelBtn = new JButton();
        submitBtn = new JButton();
        addRowBtn = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(900, 500));
        setPreferredSize(new Dimension(900, 500));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
        border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER
        , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font
        .BOLD ,12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
        new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er"
        .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(new LineBorder(Color.black, 1, true));
            panel1.setLayout(null);

            //---- label1 ----
            saleNameLabel.setText("Thanh Tung Vu");
            saleNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(saleNameLabel);
            saleNameLabel.setBounds(420, 5, 350, 25);

            //---- label2 ----
            ruleLabel.setText("Sale Employee");
            ruleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(ruleLabel);
            ruleLabel.setBounds(650, 30, 120, 25);

            //---- button1 ----
            logoutBtn.setText("Log out");
            logoutBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onLogout(e);
                }
            });
            panel1.add(logoutBtn);
            logoutBtn.setBounds(780, 5, 100, 50);

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

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(5, 140, 890, 280);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout());
        }
        add(panel2);
        panel2.setBounds(5, 75, 550, 45);

        //---- button2 ----
        cancelBtn.setText("Cancel");
        cancelBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cancelHandler(e);
            }
        });
        add(cancelBtn);
        cancelBtn.setBounds(800, 425, 90, 40);

        //---- button3 ----
        submitBtn.setText("Submit");
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                submitHandler(e);
            }
        });
        add(submitBtn);
        submitBtn.setBounds(700, 425, 90, 40);

        //---- button4 ----
        addRowBtn.setText("Add Row");
        addRowBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addRowHandler(e);
            }
        });
        add(addRowBtn);
        addRowBtn.setBounds(790, 75, 100, 45);

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
    private JLabel saleNameLabel;
    private JLabel ruleLabel;
    private JButton logoutBtn;
    private JSeparator separator1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel2;
    private JButton cancelBtn;
    private JButton submitBtn;
    private JButton addRowBtn;

    @Override
    public void display() {
        Employee employee = (Employee) appState.getAccount();
        saleNameLabel.setText(employee.getName());
        ruleLabel.setText(employee.getRole());
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
