package com.group4.itss.ui.screen.supplierorder;

import com.group4.itss.common.AppState;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.SupplierOrder;
import com.group4.itss.repository.EmployeeRepository;
import com.group4.itss.repository.SupplierOrderRepository;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.ButtonRenderer;
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
import java.util.Arrays;
import java.util.List;

@Component("supplier-order-list-screen")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SupplierOrderListScreen extends BaseScreen {
    private SupplierOrderRepository supplierOrderRepository;
    private EmployeeRepository employeeRepository;
    private Navigator navigator;
    private AppState appState;

    public SupplierOrderListScreen(SupplierOrderRepository supplierOrderRepository,
                                   EmployeeRepository employeeRepository,
                                   AppState appState,
                                   Navigator navigator) {
        this.supplierOrderRepository = supplierOrderRepository;
        this.employeeRepository = employeeRepository;
        this.navigator = navigator;
        this.appState = appState;
        initComponents();
        onCustomStyle();
    }

    @Override
    public void display() {
        Employee employee = (Employee) appState.getAccount();
        lbName.setText(employee.getName());
        loadData();
    }

    private void onCustomStyle() {
        panel2.add(Breadcrumb.create(Arrays.asList("Home", "List")));
    }

    private void loadData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] columnAttrs = {"No.", "Order Code", "Creator", "Create Date","Action"};
        List<SupplierOrder> supplierOrderList = supplierOrderRepository.findAll();
        appState.setCurSupplierOrderList(supplierOrderList);
        String data[][] = new String[supplierOrderList.size()][];
        for(int i=0;i<supplierOrderList.size();i++){
            data[i] = buildTableRowData(i,supplierOrderList.get(i));
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel(data,columnAttrs);
        table1.setModel(defaultTableModel);
        table1.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table1.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(),navigator));
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int rowIndex = table1.getSelectedRow();
                if (rowIndex >= 0) {
                    appState.setCurSupplierOrder(supplierOrderList.get(rowIndex));
                }
            }
        });
    }

    private String[] buildTableRowData(Integer index,SupplierOrder supplierOrder){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Integer no = index+1;
        String creator = new String();
        if (supplierOrder.getCreator() == null) {
            creator = "";
        }
        else creator = employeeRepository.findEmployeeById(Integer.parseInt(supplierOrder.getCreator())).getName();
        return new String[]{
                no.toString(),
                supplierOrder.getCode(),
                creator,
                formatter.format(supplierOrder.getCreatedDate()),
                "Detail"
        };
    }

    private void onBack(MouseEvent e) {
        navigator.goToHome();
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
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
        javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
        . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
        .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans.
        PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
        equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout());
        }
        add(panel2);
        panel2.setBounds(5, 75, 900, 40);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setGridColor(Color.black);
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(5, 140, 890, 300);

        //---- button2 ----
        button2.setText("Back");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onBack(e);
            }
        });
        add(button2);
        button2.setBounds(815, 446, 79, 42);

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


    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
