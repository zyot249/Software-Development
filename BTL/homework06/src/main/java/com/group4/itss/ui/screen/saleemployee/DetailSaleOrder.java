/*
 * Created by JFormDesigner on Mon Dec 09 00:06:28 ICT 2019
 */

package com.group4.itss.ui.screen.saleemployee;

import com.group4.itss.common.AppState;
import com.group4.itss.controller.MerchandiseController;
import com.group4.itss.controller.SaleOrderLineController;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.SaleOrder;
import com.group4.itss.entity.model.SaleOrderLine;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.screen.saleemployee.tablemodel.DetailSaleOrderModel;
import com.group4.itss.ui.screen.saleemployee.tablemodel.StatusCellEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

@Component("detail-sale-order")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DetailSaleOrder extends BaseScreen {
    private Navigator navigator;
    private AppState appState;
    @Autowired
    private SaleOrderLineController saleOrderLineController;
    private MerchandiseController merchandiseController;
    public DetailSaleOrder(Navigator navigator,
                           AppState appState,
                           SaleOrderLineController saleOrderLineController, MerchandiseController merchandiseController) {
        this.appState = appState;
        this.navigator = navigator;
        this.saleOrderLineController = saleOrderLineController;
        this.merchandiseController = merchandiseController;
        initComponents();
        loadData();
        customize();
    }

    private void loadData(){
        SaleOrder currentSaleOrder = appState.getCurrentSaleOrder();
        List<SaleOrderLine> saleOrderLines = new ArrayList<>();
        if (currentSaleOrder != null) {
            saleOrderLines = saleOrderLineController.getLinesByOrderCode(currentSaleOrder.getCode());
        }
        DetailSaleOrderModel detailSaleOrderModel = new DetailSaleOrderModel(saleOrderLines, merchandiseController) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 5;
            }
        };
        table1.setModel(detailSaleOrderModel);
        table1.getColumn("Status").setCellEditor(new StatusCellEditor());
    }

    private void customize(){
        panel2.add(Breadcrumb.create(Arrays.asList("Home","View Sale Order",appState.getCurrentSaleOrder().getCode())));
    }

    private void logOutHandler(MouseEvent e) {
        navigator.onLogout();
    }

    private void backHandler(MouseEvent e) {
        navigator.navigate("view-sale-order");
    }

    private void processHandler(MouseEvent e) {
        navigator.navigate("create-supplier-order-screen");
    }

    private void saveHandler(MouseEvent e){
        saleOrderLineController.saveNewStatus(((DetailSaleOrderModel) table1.getModel()).getSaleOrderLineList());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Thanh Tung
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        button1 = new JButton();
        separator1 = new JSeparator();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(900, 500));
        setBorder (new CompoundBorder( new TitledBorder (new EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", TitledBorder. CENTER, TitledBorder. BOTTOM, new Font ("D\u0069alog"
        , Font .BOLD ,12 ), Color. red) , getBorder
        ( )) );  addPropertyChangeListener (new PropertyChangeListener( ){ @Override public void propertyChange (PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException
        ( ); }} );
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

            //---- button1 ----
            button1.setText("Log out");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    logOutHandler(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(780, 5, 100, 50);

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
            panel2.setLayout(new GridLayout(1, 3));
        }
        add(panel2);
        panel2.setBounds(5, 75, 890, 45);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(5, 140, 890, 280);

        //---- button4 ----
        button4.setText("Back");
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backHandler(e);
            }
        });
        add(button4);
        button4.setBounds(800, 430, 90, 40);

        //---- button5 ----
        button5.setText("Process");
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                processHandler(e);
            }
        });
        add(button5);
        button5.setBounds(710, 430, 90, 40);

        // ---- button6 ----
        button6.setText("Save");
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveHandler(e);
            }
        });
        add(button6);
        button6.setBounds(620, 430, 90, 40);

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
    private JButton button1;
    private JSeparator separator1;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    @Override
    public void display() {
        Employee employee = (Employee) appState.getAccount();
        label1.setText(employee.getName());
        label2.setText(employee.getRole());
    }

    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
