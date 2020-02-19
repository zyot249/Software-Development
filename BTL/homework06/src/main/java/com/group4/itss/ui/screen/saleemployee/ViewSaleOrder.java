/*
 * Created by JFormDesigner on Tue Dec 03 23:45:33 ICT 2019
 */

package com.group4.itss.ui.screen.saleemployee;

import com.group4.itss.common.AppState;
import com.group4.itss.controller.SaleOrderController;
import com.group4.itss.controller.SaleOrderLineController;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.SaleOrder;
import com.group4.itss.repository.SaleOrderRepository;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.ButtonRenderer;
import com.group4.itss.ui.screen.saleemployee.tablemodel.ButtonEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("view-sale-order")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ViewSaleOrder extends BaseScreen {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private Navigator navigator;
    private AppState appState;
    @Autowired
    private SaleOrderController saleOrderController;

    public ViewSaleOrder(Navigator navigator, AppState appState, SaleOrderController saleOrderController) {
        this.navigator = navigator;
        this.appState = appState;
        this.saleOrderController = saleOrderController;
        initComponents();
        customize();
        loadData();
    }

    @Override
    public void display() {
        Employee employee = (Employee) appState.getAccount();
        label1.setText(employee.getName());
        label2.setText(employee.getRole());
    }

    private void loadData(){
        List<SaleOrder> saleOrders = saleOrderController.getAllSaleOrders();
        String columnAttrs[] = {"No.", "Sale order code", "Status", "Created Time","Action"};
        String[][] data = new String[saleOrders.size()][];
        for (int i = 0; i < saleOrders.size(); i++) {
            data[i] = buildTableRowData(i, saleOrders.get(i));
        }
        DefaultTableModel model = new DefaultTableModel(data, columnAttrs){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        table1.setModel(model);
        table1.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table1.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), navigator,appState));
        List<SaleOrder> finalSaleOrders = saleOrders;
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRowIndex = table1.getSelectedRow();
                appState.setCurrentSaleOrder(finalSaleOrders.get(selectedRowIndex));
            }
        });
    }

    private String[] buildTableRowData(Integer index, SaleOrder saleOrder) {
        Integer no = index+1;
        return new String[]{
                no.toString(),
                saleOrder.getCode(),
                saleOrder.getStatus().toString(),
                formatter.format(saleOrder.getCreatedDate()),
                "Detail"
        };
    }

    private void customize(){
        panel2.add(Breadcrumb.create(Arrays.asList("Home","View Sale Order")));
    }

    private void logOutHandler(MouseEvent e) {
        navigator.onLogout();
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
        button2 = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(900, 500));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
        0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
        .BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.
        red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
        beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}});
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
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(5, 140, 890, 280);

        //---- button2 ----
        button2.setText("Back");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backHandler(e);
            }
        });
        add(button2);
        button2.setBounds(800, 430, 90, 40);

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
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void backHandler(MouseEvent event){
        navigator.goToHome();
    }


}
