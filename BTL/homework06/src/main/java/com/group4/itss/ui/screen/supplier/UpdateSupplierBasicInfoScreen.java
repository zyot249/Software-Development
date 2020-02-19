/*
 * Created by JFormDesigner on Tue Dec 03 22:05:07 ICT 2019
 */

package com.group4.itss.ui.screen.supplier;

import com.group4.itss.common.Constants;
import com.group4.itss.controller.SupplierController;
import com.group4.itss.entity.model.Supplier;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.PopUp;
import com.group4.itss.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Objects;

@Component("update-supplier-info-screen")
public class UpdateSupplierBasicInfoScreen extends BaseScreen {

    @Autowired
    private SupplierController supplierController;

    private Supplier supplier;

    public UpdateSupplierBasicInfoScreen() {
        initComponents();
        bcSupplierInfo.add(Breadcrumb.create(Collections.singletonList("Supplier's Information")));
        bcSupplierInfo.setAlignmentY(BOTTOM_ALIGNMENT);
        bcDelivery.add(Breadcrumb.create(Collections.singletonList("Delivery")));
        bcDelivery.setAlignmentY(BOTTOM_ALIGNMENT);
    }

    private void initData(Supplier supplier) {
        txtfName.setText(supplier.getName());
        txtfPhone.setText(supplier.getPhone());
        txtfNumOfDeliveryDays.setText(supplier.getNumberOfDeliveryDays().toString());
        if (supplier.getAddress() != null) {
            txtfAddress.setText(supplier.getAddress());
        }

        if (supplier.getDescription() != null) {
            txtaDescription.setText(supplier.getDescription());
        }

        if (supplier.getWebsite() != null) {
            txtfWebsite.setText(supplier.getWebsite());
        }

        cmboxTransportation.setSelectedItem(supplier.getTransportation());
    }

    private void onBtnCancelActionPerformed(ActionEvent e) {
        showConfirmDialog("Cancel", Constants.MESSAGES.CANCEL_UPDATE_INFO_CONFIRM_MSG);
    }

    private void showConfirmDialog(String title, String msg) {
        int opt = PopUp.render(PopUp.PopupTypes.CONFIRM, msg, title);
        if (opt == 2) {
            if (title.equals("Cancel")) {
                close();
            } else if (title.equals("Update")) {
                updateBasicInfo();
            }
        }
    }

    private void updateBasicInfo() {
        String websiteUrl = txtfWebsite.getText();
        String description = txtaDescription.getText();
        int numOfDeliveryDays = Integer.parseInt(txtfNumOfDeliveryDays.getText());
        String name = txtfName.getText();
        String phone = txtfPhone.getText();
        String transportation = Objects.requireNonNull(cmboxTransportation.getSelectedItem()).toString();
        String address = txtfAddress.getText();

        Supplier newSupplier = new Supplier(supplier.getEmail(),
                supplier.getPassword(),
                name,
                phone,
                address,
                websiteUrl,
                description,
                transportation,
                numOfDeliveryDays);
        newSupplier.setId(supplier.getId());
        newSupplier.setCode(supplier.getCode());

        try {
            supplierController.updateSupplier(newSupplier);
            showSuccessDialog("Success", Constants.MESSAGES.UPDATE_SUCCESS_MSG);
            appState.setAccount(newSupplier);
            close();
        } catch (Exception e) {
            displayErrorMessage(Constants.MESSAGES.ERROR_MSG);
            e.printStackTrace();
        }
    }

    private void showSuccessDialog(String title, String msg) {
        PopUp.render(PopUp.PopupTypes.SUCCESS, msg, title);
    }

    private void displayErrorMessage(String msg) {
        if (msg.equals(""))
            lbErrorMessage.setVisible(false);
        else {
            lbErrorMessage.setVisible(true);
            lbErrorMessage.setText(msg);
        }
    }

    private boolean validateInput(String name, String phone) {
        if (StringUtils.isBlank(phone)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_PHONE_MSG);
            return false;
        } else {
            if (!phone.matches(Constants.PHONE_PATTERN)) {
                displayErrorMessage(Constants.MESSAGES.WRONG_PHONE_MSG);
                return false;
            }
        }
        if (StringUtils.isBlank(name)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_NAME_MSG);
            return false;
        }

        displayErrorMessage("");
        return true;
    }

    private void close() {
        navigator.navigate("supplier-home-screen");
    }

    private void onBtnUpdateActionPerformed(ActionEvent e) {
        String name = txtfName.getText();
        String phone = txtfPhone.getText();
        if (validateInput(name, phone)) {
            showConfirmDialog("Update", Constants.MESSAGES.SUBMIT_CONFIRM_MSG);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - shyn
        dialogPane = new JPanel();
        btnCancel = new JButton();
        btnUpdate = new JButton();
        lbName = new JLabel();
        lbPhone = new JLabel();
        lbAddress = new JLabel();
        lbWebsite = new JLabel();
        lbDescription = new JLabel();
        txtfName = new JTextField();
        txtfPhone = new JTextField();
        txtfAddress = new JTextField();
        txtfWebsite = new JTextField();
        scrpDescription = new JScrollPane();
        txtaDescription = new JTextArea();
        txtfNumOfDeliveryDays = new JFormattedTextField();
        lbNumOfDeliveryDays = new JLabel();
        lbTransportation = new JLabel();
        cmboxTransportation = new JComboBox<>();
        lbErrorMessage = new JLabel();
        lbTitle = new JLabel();
        separator1 = new JSeparator();
        bcSupplierInfo = new JPanel();
        bcDelivery = new JPanel();

        //======== this ========
        setLayout(null);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(null);

            //---- btnCancel ----
            btnCancel.setText("Cancel");
            btnCancel.addActionListener(this::onBtnCancelActionPerformed);
            dialogPane.add(btnCancel);
            btnCancel.setBounds(235, 445, 180, 33);

            //---- btnUpdate ----
            btnUpdate.setText("Update");
            btnUpdate.setBackground(Color.white);
            btnUpdate.addActionListener(this::onBtnUpdateActionPerformed);
            dialogPane.add(btnUpdate);
            btnUpdate.setBounds(485, 445, 180, 33);

            //---- lbName ----
            lbName.setText("Name*:");
            dialogPane.add(lbName);
            lbName.setBounds(15, 136, 130, 20);

            //---- lbPhone ----
            lbPhone.setText("Phone*:");
            dialogPane.add(lbPhone);
            lbPhone.setBounds(15, 166, 130, 20);

            //---- lbAddress ----
            lbAddress.setText("Address:");
            dialogPane.add(lbAddress);
            lbAddress.setBounds(15, 196, 130, 20);

            //---- lbWebsite ----
            lbWebsite.setText("Website:");
            dialogPane.add(lbWebsite);
            lbWebsite.setBounds(15, 226, 130, 20);

            //---- lbDescription ----
            lbDescription.setText("Description");
            dialogPane.add(lbDescription);
            lbDescription.setBounds(15, 256, 130, 20);

            //---- txtfName ----
            txtfName.setBorder(new LineBorder(Color.gray, 1, true));
            dialogPane.add(txtfName);
            txtfName.setBounds(145, 136, 250, 20);

            //---- txtfPhone ----
            txtfPhone.setBorder(new LineBorder(Color.gray, 1, true));
            dialogPane.add(txtfPhone);
            txtfPhone.setBounds(145, 166, 250, 20);

            //---- txtfAddress ----
            txtfAddress.setBorder(new LineBorder(Color.gray, 1, true));
            dialogPane.add(txtfAddress);
            txtfAddress.setBounds(145, 196, 250, 20);

            //---- txtfWebsite ----
            txtfWebsite.setBorder(new LineBorder(Color.gray, 1, true));
            dialogPane.add(txtfWebsite);
            txtfWebsite.setBounds(145, 226, 250, 20);

            //======== scrpDescription ========
            {
                scrpDescription.setBorder(new LineBorder(Color.gray, 1, true));

                //---- txtaDescription ----
                txtaDescription.setBorder(null);
                scrpDescription.setViewportView(txtaDescription);
            }
            dialogPane.add(scrpDescription);
            scrpDescription.setBounds(145, 256, 250, 105);

            //---- txtfNumOfDeliveryDays ----
            txtfNumOfDeliveryDays.setBorder(new LineBorder(Color.gray, 1, true));
            txtfNumOfDeliveryDays.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            txtfNumOfDeliveryDays.setText("1");
            dialogPane.add(txtfNumOfDeliveryDays);
            txtfNumOfDeliveryDays.setBounds(600, 216, 63, 20);

            //---- lbNumOfDeliveryDays ----
            lbNumOfDeliveryDays.setText("<html>Number of<br>delivery days*:</html>");
            dialogPane.add(lbNumOfDeliveryDays);
            lbNumOfDeliveryDays.setBounds(466, 196, 130, 40);

            //---- lbTransportation ----
            lbTransportation.setText("Transportation:");
            dialogPane.add(lbTransportation);
            lbTransportation.setBounds(466, 166, 130, 20);

            //---- cmboxTransportation ----
            cmboxTransportation.setBorder(null);
            cmboxTransportation.setModel(new DefaultComboBoxModel<>(Constants.TRANSPORTATION_TYPES));
            dialogPane.add(cmboxTransportation);
            cmboxTransportation.setBounds(596, 168, 127, 20);

            //---- lbErrorMessage ----
            lbErrorMessage.setText("Error");
            lbErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
            lbErrorMessage.setForeground(Color.red);
            lbErrorMessage.setVisible(false);
            dialogPane.add(lbErrorMessage);
            lbErrorMessage.setBounds(150, 380, 600, 50);

            //---- lbTitle ----
            lbTitle.setText("Update basic information");
            lbTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
            lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
            dialogPane.add(lbTitle);
            lbTitle.setBounds(256, 10, 400, 70);

            //---- separator1 ----
            separator1.setBackground(Color.gray);
            separator1.setForeground(Color.lightGray);
            separator1.setOrientation(SwingConstants.VERTICAL);
            dialogPane.add(separator1);
            separator1.setBounds(440, 125, 15, 236);

            //======== bcSupplierInfo ========
            {
                bcSupplierInfo.setLayout(new GridLayout());
            }
            dialogPane.add(bcSupplierInfo);
            bcSupplierInfo.setBounds(0, 100, 150, 35);

            //======== bcDelivery ========
            {
                bcDelivery.setLayout(new GridLayout());
            }
            dialogPane.add(bcDelivery);
            bcDelivery.setBounds(445, 100, 150, 35);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane.setMinimumSize(preferredSize);
                dialogPane.setPreferredSize(preferredSize);
            }
        }
        add(dialogPane);
        dialogPane.setBounds(0, 0, 900, 500);

        setPreferredSize(new Dimension(900, 500));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - shyn
    private JPanel dialogPane;
    private JButton btnCancel;
    private JButton btnUpdate;
    private JLabel lbName;
    private JLabel lbPhone;
    private JLabel lbAddress;
    private JLabel lbWebsite;
    private JLabel lbDescription;
    private JTextField txtfName;
    private JTextField txtfPhone;
    private JTextField txtfAddress;
    private JTextField txtfWebsite;
    private JScrollPane scrpDescription;
    private JTextArea txtaDescription;
    private JFormattedTextField txtfNumOfDeliveryDays;
    private JLabel lbNumOfDeliveryDays;
    private JLabel lbTransportation;
    private JComboBox<String> cmboxTransportation;
    private JLabel lbErrorMessage;
    private JLabel lbTitle;
    private JSeparator separator1;
    private JPanel bcSupplierInfo;
    private JPanel bcDelivery;

    @Override
    public void display() {
        txtfNumOfDeliveryDays.setFormatterFactory(new DefaultFormatterFactory(Utils.getNumberFormatter()));
        supplier = (Supplier) appState.getAccount();
        initData(supplier);
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
