package com.group4.itss.ui.screen.supplierorder.createsupplierorder;


import com.group4.itss.common.AppState;
import com.group4.itss.controller.ProvidedMerchandiseController;
import com.group4.itss.controller.SupplierOrderController;
import com.group4.itss.controller.model.ProvidedMerchandiseResponse;
import com.group4.itss.entity.enumeration.ProvidedMerchandiseStatus;
import com.group4.itss.entity.enumeration.SaleOrderLineStatus;
import com.group4.itss.entity.enumeration.SaleOrderStatus;
import com.group4.itss.entity.model.*;
import com.group4.itss.repository.*;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.PopUp;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component("create-supplier-order-screen")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateSupplierOrderScreen extends BaseScreen {
    private ProvidedMerchandiseController providedMerchandiseController;
    private SupplierOrderLineRepository supplierOrderLineRepository;
    private ProvidedMerchandiseRepository providedMerchandiseRepository;
    private SaleOrderLineRepository saleOrderLineRepository;
    private SupplierOrderRepository supplierOrderRepository;
    private SupplierOrderController supplierOrderController;
    private SaleOrderRepository saleOrderRepository;
    private Navigator navigator;
    private AppState appState;

    private CreateSupplierOrderScreen(SaleOrderLineRepository saleOrderLineRepository,
                                      ProvidedMerchandiseController providedMerchandiseController,
                                      SupplierOrderRepository supplierOrderRepository,
                                      SupplierOrderLineRepository supplierOrderLineRepository,
                                      Navigator navigator,
                                      ProvidedMerchandiseRepository providedMerchandiseRepository,
                                      SaleOrderRepository saleOrderRepository,
                                      SupplierOrderController supplierOrderController,
                                      AppState appState) {

        this.providedMerchandiseController = providedMerchandiseController;
        this.providedMerchandiseRepository = providedMerchandiseRepository;
        this.saleOrderLineRepository = saleOrderLineRepository;
        this.supplierOrderRepository = supplierOrderRepository;
        this.supplierOrderLineRepository = supplierOrderLineRepository;
        this.supplierOrderController = supplierOrderController;
        this.saleOrderRepository = saleOrderRepository;

        this.navigator = navigator;
        this.appState = appState;
        initComponents();
        onCustomStyle();
    }

    @Override
    public void display() {
        Employee employee = (Employee) appState.getAccount();
        lbName.setText(employee.getName());
    }

    private void onCustomStyle() {
        onCustomSupplierOrderTable();
        onCustomSaleOrderTable();
        panel2.add(Breadcrumb.create(Arrays.asList("Home", "Create")));
    }

    private void onCustomSupplierOrderTable() {
        supplierOrderTable.setModel(new SupplierOrderTableModel());
        List<ProvidedMerchandiseResponse> providedMerchandiseList =
                providedMerchandiseController.getAllResponse();
        supplierOrderTable
                .getColumn("Supplier")
                .setCellEditor(new SupplierCellEditor(providedMerchandiseList));
    }

    private void onCustomSaleOrderTable() {
        SaleOrder currentSaleOrder = appState.getCurrentSaleOrder();
        List<SaleOrderLine> saleOrderLines = saleOrderLineRepository.findByOrderCode(currentSaleOrder.getCode());
        saleOrderTable.setModel(new SaleOrderTableModel(saleOrderLines));
        saleOrderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                SaleOrderTableModel model = (SaleOrderTableModel) saleOrderTable.getModel();
                int rowIndex = saleOrderTable.getSelectedRow();
                if (rowIndex >= 0) {
                    SaleOrderLine saleOrderLine = model.getSaleOrderLines().get(rowIndex);
                    appState.setSelectedSaleOrderLine(saleOrderLine);
                }
            }
        });
    }

    private void onSubmit(MouseEvent e) {
        PopUp.render(
                PopUp.PopupTypes.CONFIRM,
                "Are you sure to submit",
                "Submit Supplier Order"
        );
        SupplierOrderTableModel supplierOrderTableModel = (SupplierOrderTableModel) supplierOrderTable.getModel();
        SaleOrderTableModel saleOrderTableModel = (SaleOrderTableModel) saleOrderTable.getModel();
        List<SupplierOrderLine> supplierOrderLineList = supplierOrderTableModel.getSupplierOrderLineList();
        List<ProvidedMerchandise> providedMerchandiseList = supplierOrderTableModel.getProvidedMerchandiseList();
        List <SaleOrderLine> saleOrderLineList = saleOrderTableModel.getSaleOrderLines();

        int completeSaleOrderLine = 0;
        for (SaleOrderLine saleOrderLine: saleOrderLineList) {
            int totalQuanity = 0;
            for (SupplierOrderLine supplierOrderLine:supplierOrderLineList) {
                if (supplierOrderLine.getMerchandiseCode().equals(saleOrderLine.getMerchandiseCode())) {
                    totalQuanity += supplierOrderLine.getQuantity();
                }
            }
            if (totalQuanity < saleOrderLine.getQuantity()) {
                PopUp.render(
                        PopUp.PopupTypes.ERROR,
                        "Ops! the quantity for " + saleOrderLine.getMerchandiseCode() + " is not enough!",
                        "Submit Supplier Order!"
                );
                onResetSupplierOrderTable();
                return;
            }
            saleOrderLine.setStatus(SaleOrderLineStatus.DONE);
            completeSaleOrderLine ++;
        }

        if (completeSaleOrderLine == saleOrderLineList.size()) {
            appState.getCurrentSaleOrder().setStatus(SaleOrderStatus.DONE);
        }

        SupplierOrder supplierOrder = createSupplierOrder();

        for (int i = 0; i < supplierOrderLineList.size(); i++) {
            SupplierOrderLine supplierOrderLine = supplierOrderLineList.get(i);
            ProvidedMerchandise providedMerchandise = providedMerchandiseList.get(i);
            supplierOrderLine.setOrderCode(supplierOrder.getCode());
            supplierOrderLine.setMerchandiseCode(providedMerchandise.getMerchandiseCode());
            providedMerchandise.setStatus(ProvidedMerchandiseStatus.closed);
        }

        try {
            supplierOrderRepository.save(supplierOrder);
            supplierOrderLineRepository.saveAll(supplierOrderLineList);
            saleOrderRepository.save(appState.getCurrentSaleOrder());
            saleOrderLineRepository.saveAll(saleOrderLineList);
            providedMerchandiseRepository.saveAll(providedMerchandiseList);
            PopUp.render(
                    PopUp.PopupTypes.SUCCESS,
                    "Create Supplier Order Success!",
                    "Submit Supplier Order!"
            );
            navigator.goToHome();
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            PopUp.render(
                    PopUp.PopupTypes.ERROR,
                    "Ops! Something went wrong!",
                    "Submit Supplier Order!"
            );
        }

        onResetForm();
    }

    private SupplierOrder createSupplierOrder() {
        SupplierOrder supplierOrder = new SupplierOrder();
        supplierOrder.setCode(createCode());
        supplierOrder.setCreatedDate(Calendar.getInstance().getTime());
        supplierOrder.setCreator(appState.getAccount().getId().toString());
        return supplierOrder;
    }

    private String createCode() {
        return supplierOrderController.getNextSupplierOrderCode();
    }

    private void onAddSupplier(MouseEvent e) {
        SaleOrderLine saleOrderLine = appState.getSelectedSaleOrderLine();
        if (saleOrderLine == null) {
            PopUp.render(
                    PopUp.PopupTypes.ERROR,
                    "Please! Select a merchandise",
                    "Submit Supplier Order!"
            );
            return;
        }
        SupplierOrderTableModel model = (SupplierOrderTableModel) supplierOrderTable.getModel();
        SupplierOrderLine supplierOrderLine = new SupplierOrderLine();
        supplierOrderLine.setMerchandiseCode(saleOrderLine.getMerchandiseCode());
        model.addRow(supplierOrderLine);
    }

    private void onResetForm() {
        SupplierOrderTableModel supplierOrderTableModel = (SupplierOrderTableModel) supplierOrderTable.getModel();
        SaleOrderTableModel saleOrderTableModel = (SaleOrderTableModel) saleOrderTable.getModel();

        supplierOrderTableModel.getSupplierOrderLineList().clear();
        supplierOrderTableModel.getProvidedMerchandiseList().clear();
        saleOrderTableModel.getSaleOrderLines().clear();

        supplierOrderTableModel.fireTableDataChanged();
        saleOrderTableModel.fireTableDataChanged();
    }

    private void onResetSupplierOrderTable() {
        SupplierOrderTableModel supplierOrderTableModel = (SupplierOrderTableModel) supplierOrderTable.getModel();
        supplierOrderTableModel.getSupplierOrderLineList().clear();
        supplierOrderTableModel.getProvidedMerchandiseList().clear();
        supplierOrderTableModel.fireTableDataChanged();
    }

    private void onCancel(MouseEvent e) {
        onResetForm();
        navigator.goToHome();
    }

    private void btLogoutMouseClicked(MouseEvent e) {
        navigator.onLogout();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Thanh Dao Ngoc
        scrollPane1 = new JScrollPane();
        supplierOrderTable = new JTable();
        panel2 = new JPanel();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        scrollPane2 = new JScrollPane();
        saleOrderTable = new JTable();
        header = new JPanel();
        lbName = new JLabel();
        lbRole = new JLabel();
        btnLogout = new JButton();
        separator2 = new JSeparator();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
        . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax
        . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,
        12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans
        . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .
        getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- supplierordertable ----
            supplierOrderTable.setBorder(LineBorder.createBlackLineBorder());
            supplierOrderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPane1.setViewportView(supplierOrderTable);
        }
        add(scrollPane1);
        scrollPane1.setBounds(6, 279, 888, 260);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout());
        }
        add(panel2);
        panel2.setBounds(0, 75, 508, 40);

        //---- button2 ----
        button2.setText("SUBMIT");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onSubmit(e);
            }
        });
        add(button2);
        button2.setBounds(605, 80, 80, button2.getPreferredSize().height);

        //---- button3 ----
        button3.setText("CANCEL");
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onCancel(e);
            }
        });
        add(button3);
        button3.setBounds(685, 80, 80, button3.getPreferredSize().height);

        //---- button4 ----
        button4.setText("ADD SUPPLIER");
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onAddSupplier(e);
            }
        });
        add(button4);
        button4.setBounds(770, 80, 120, button4.getPreferredSize().height);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(saleOrderTable);
        }
        add(scrollPane2);
        scrollPane2.setBounds(6, 131, 888, 142);

        //======== header ========
        {
            header.setBorder(LineBorder.createBlackLineBorder());
            header.setLayout(null);

            //---- lbName ----
            lbName.setText("SALE");
            lbName.setHorizontalAlignment(SwingConstants.RIGHT);
            header.add(lbName);
            lbName.setBounds(440, 5, 350, 25);

            //---- lbRole ----
            lbRole.setText("SALE Employee");
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
    // Generated using JFormDesigner Evaluation license - Thanh Dao Ngoc
    private JScrollPane scrollPane1;
    private JTable supplierOrderTable;
    private JPanel panel2;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JScrollPane scrollPane2;
    private JTable saleOrderTable;
    private JPanel header;
    private JLabel lbName;
    private JLabel lbRole;
    private JButton btnLogout;
    private JSeparator separator2;


    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
