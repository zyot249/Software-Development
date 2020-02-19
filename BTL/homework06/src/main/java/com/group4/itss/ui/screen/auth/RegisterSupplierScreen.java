package com.group4.itss.ui.screen.auth;

import com.group4.itss.common.Constants;
import com.group4.itss.controller.AccountController;
import com.group4.itss.controller.SupplierController;
import com.group4.itss.entity.model.Supplier;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.PopUp;
import com.group4.itss.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.util.Collections;
import java.util.Objects;

@Component("register-supplier-screen")
public class RegisterSupplierScreen extends BaseScreen {

    @Autowired
    private SupplierController supplierController;

    @Autowired
    private AccountController accountController;

    private JButton btnSignUp;
    private JButton btnCancel;
    private JFormattedTextField txtfEmail;
    private JPasswordField txtfPassword;
    private JPasswordField txtfConfirmPassword;
    private JFormattedTextField txtfName;
    private JFormattedTextField txtfPhone;
    private JFormattedTextField txtfAddress;
    private JFormattedTextField txtfWebsiteUrl;
    private JTextArea txtaDescription;
    private JFormattedTextField txtfNumOfDeliveryDays;
    private JComboBox<String> cmboxTransportation;
    private JScrollPane scrpDescription;
    private JCheckBox cbAgreeTerm;
    private JLabel lbErrorMessage;
    private JSeparator separator1;
    private JPanel bcAccount = new JPanel(new GridLayout());
    private JPanel bcDelivery = new JPanel(new GridLayout());
    private JPanel bcSupplierInfo = new JPanel(new GridLayout());

    public RegisterSupplierScreen() {
        super();
        setSize(900,500);
        initComponents();
        setLayout(null);
    }

    private void clearData() {
        txtaDescription.setText("");
        txtfAddress.setText("");
        txtfConfirmPassword.setText("");
        txtfEmail.setText("");
        txtfName.setText("");
        txtfPassword.setText("");
        txtfNumOfDeliveryDays.setText("1");
        txtfWebsiteUrl.setText("");
        txtfPhone.setText("+84 ");
        cbAgreeTerm.setSelected(false);
        btnSignUp.setEnabled(false);
    }

    private void initComponents() {
        btnSignUp = new JButton("Sign Up");
        btnCancel = new JButton("Cancel");
        txtfEmail = new MyEditText(146, 136);
        txtfPassword = new JPasswordField();
        txtfConfirmPassword = new JPasswordField();
        txtfName = new MyEditText(596, 136);
        txtfPhone = new MyEditText(596, 166);
        txtfAddress = new MyEditText(596, 196);
        txtfWebsiteUrl = new MyEditText(596, 226);
        txtfNumOfDeliveryDays = new MyEditText(146, 288);
        txtfNumOfDeliveryDays.setSize(63, 20);
        txtaDescription = new JTextArea();
        cmboxTransportation = new JComboBox<>(Constants.TRANSPORTATION_TYPES);
        scrpDescription = new JScrollPane();
        cbAgreeTerm = new JCheckBox("I agree the terms of the agreement");
        separator1 = new JSeparator();


        btnSignUp.setBounds(485, 445, 180, 33);
        btnSignUp.setEnabled(false);
        add(btnSignUp);
        btnCancel.setBounds(235, 445, 180, 33);
        add(btnCancel);

        MyLabel lbEmail = new MyLabel("Email*:", 16, 136, txtfEmail);
        add(lbEmail);
        add(txtfEmail);

        MyLabel lbPassword = new MyLabel("Password*:", 16, 166, txtfPassword);
        add(lbPassword);
        txtfPassword.setBounds(146, 166, 250, 20);
        txtfPassword.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(txtfPassword);

        MyLabel lbConfirmPassword = new MyLabel("Confirm password*:", 16, 196, txtfConfirmPassword);
        add(lbConfirmPassword);
        txtfConfirmPassword.setBounds(146, 196, 250, 20);
        txtfConfirmPassword.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(txtfConfirmPassword);

        MyLabel lbName = new MyLabel("Name*:", 466, 136, txtfName);
        add(lbName);
        add(txtfName);

        MyLabel lbPhone = new MyLabel("Phone*:", 466, 166, txtfPhone);
        txtfPhone.setText("+84 ");
        add(lbPhone);
        add(txtfPhone);

        MyLabel lbAddress = new MyLabel("Address:", 466, 196, txtfAddress);
        add(lbAddress);
        add(txtfAddress);

        MyLabel lbWebsiteUrl = new MyLabel("Website:", 466, 226, txtfWebsiteUrl);
        add(lbWebsiteUrl);
        add(txtfWebsiteUrl);

        MyLabel lbDescription = new MyLabel("Description:", 466, 256, txtaDescription);
        txtaDescription.setBounds(596, 256, 250, 105);
        add(lbDescription);
        scrpDescription.setViewportView(txtaDescription);
        scrpDescription.setBounds(596, 256, 250, 105);
        scrpDescription.setBorder(new LineBorder(Color.GRAY, 1, true));
        add(scrpDescription);

        MyLabel lbNumOfDeliveryDays = new MyLabel("<html>Number of<br>delivery days*:</html>",
                16, 288, txtfNumOfDeliveryDays);
        lbNumOfDeliveryDays.setSize(130, 40);
        txtfNumOfDeliveryDays.setFormatterFactory(new DefaultFormatterFactory(Utils.getNumberFormatter()));
        txtfNumOfDeliveryDays.setText("1");
        add(lbNumOfDeliveryDays);
        add(txtfNumOfDeliveryDays);

        MyLabel lbBy = new MyLabel("By", 231, 288, cmboxTransportation);
        lbBy.setSize(25, 20);
        cmboxTransportation.setBounds(256, 284, 140, 26);
        add(lbBy);
        add(cmboxTransportation);

        lbErrorMessage = new JLabel("");
        lbErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lbErrorMessage.setForeground(Color.red);
        add(lbErrorMessage);
        lbErrorMessage.setBounds(150, 380, 600, 25);

        JLabel lbTitle = new JLabel("Register to be a Supplier");
        lbTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        add(lbTitle);
        lbTitle.setBounds(256, 10, 400, 70);

        cbAgreeTerm.setBounds(332, 420, 285, 19);
        add(cbAgreeTerm);

        separator1.setBackground(Color.gray);
        separator1.setForeground(Color.lightGray);
        separator1.setOrientation(SwingConstants.VERTICAL);
        add(separator1);
        separator1.setBounds(440, 125, 15, 236);

        bcAccount.setBounds(0, 100, 150, 35);
        bcAccount.add(Breadcrumb.create(Collections.singletonList("Account")));
        bcDelivery.setBounds(0, 246, 150, 35);
        bcDelivery.add(Breadcrumb.create(Collections.singletonList("Delivery")));
        bcSupplierInfo.setBounds(445, 100, 150, 35);
        bcSupplierInfo.add(Breadcrumb.create(Collections.singletonList("Supplier's Information")));
        add(bcAccount);
        add(bcDelivery);
        add(bcSupplierInfo);

        btnCancel.addActionListener(e -> onCancelAction());
        btnSignUp.addActionListener(e -> onSignupAction());
        cbAgreeTerm.addActionListener(e -> onClickCheckboxAction());
    }

    private void onSignupAction() {
        String email = txtfEmail.getText();
        String password = txtfPassword.getText();
        String cfPassword = txtfConfirmPassword.getText();
        String name = txtfName.getText();
        String phone = txtfPhone.getText();
        if (validateInput(email, password, cfPassword, name, phone)) {
            showConfirmDialog("Submit", Constants.MESSAGES.SUBMIT_CONFIRM_MSG);
        }
    }

    private void onCancelAction() {
        showConfirmDialog("Cancel", Constants.MESSAGES.CANCEL_SIGN_UP_CONFIRM_MSG);
    }

    private void onClickCheckboxAction() {
        if (cbAgreeTerm.isSelected()) {
            btnSignUp.setEnabled(true);
        } else {
            btnSignUp.setEnabled(false);
        }
    }

    public boolean validateInput(String email,
                                 String password,
                                 String cfPassword,
                                 String name,
                                 String phone) {

        if (StringUtils.isBlank(email)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_EMAIL_MSG);
            return false;
        }

        if (!email.matches(Constants.EMAIL_PATTERN)) {
            displayErrorMessage(Constants.MESSAGES.WRONG_EMAIL_MSG);
            return false;
        }

        if (StringUtils.isBlank(password)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_PASSWORD_MSG);
            return false;
        } else {
            if (password.length() < Constants.MIN_PASSWORD_CHARACTERS) {
                displayErrorMessage(Constants.MESSAGES.TOO_SHORT_PASSWORD_MSG);
                return false;
            }
        }

        if (StringUtils.isBlank(cfPassword)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_CONFIRM_PASSWORD_MSG);
            return false;
        } else {
            if (!cfPassword.equals(password)) {
                displayErrorMessage(Constants.MESSAGES.WRONG_CONFIRM_PASSWORD_MSG);
                return false;
            }
        }

        if (StringUtils.isBlank(name)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_NAME_MSG);
            return false;
        }

        if (StringUtils.isBlank(phone)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_PHONE_MSG);
            return false;
        } else {
            if (!phone.matches(Constants.PHONE_PATTERN)) {
                displayErrorMessage(Constants.MESSAGES.WRONG_PHONE_MSG);
                return false;
            }
        }

        displayErrorMessage("");
        return true;
    }

    private void displayErrorMessage(String msg) {
        if (msg.equals(""))
            lbErrorMessage.setVisible(false);
        else {
            lbErrorMessage.setVisible(true);
            lbErrorMessage.setText(msg);
        }
    }

    private void showConfirmDialog(String title, String msg) {
        int opt = PopUp.render(PopUp.PopupTypes.CONFIRM, msg, title);
        if (opt == 2) {
            if (title.equals("Cancel")) {
                close();
            } else if (title.equals("Submit")) {
                registerSupplier();
            }
        }
    }

    private void registerSupplier() {
        try {
            if (accountController.isExistedEmail(txtfEmail.getText())) {
                displayErrorMessage("Email is existed! Please choose another one!");
            } else {
                String email = txtfEmail.getText();
                String password = txtfPassword.getText();
                String name = txtfName.getText();
                String phone = txtfPhone.getText();
                String transportation = Objects.requireNonNull(cmboxTransportation.getSelectedItem()).toString();
                String address = txtfAddress.getText();
                String websiteUrl = txtfWebsiteUrl.getText();
                String description = txtaDescription.getText();
                int numOfDeliveryDays = Integer.parseInt(txtfNumOfDeliveryDays.getText());

                Supplier supplier = new Supplier(email,
                        password,
                        name,
                        phone,
                        address,
                        websiteUrl,
                        description,
                        transportation,
                        numOfDeliveryDays);

                supplierController.createSupplier(supplier);
                showSuccessDialog("Successful Register", "Register successfully!");
                appState.setNewAccount(supplier);
                close();
            }
        } catch (Exception e) {
            displayErrorMessage("Something's wrong! Please try again after minutes!");
            e.printStackTrace();
        }
    }

    private void showSuccessDialog(String title, String msg) {
        PopUp.render(PopUp.PopupTypes.SUCCESS, msg, title);
    }

    private void close() {
        navigator.navigate("login-screen");
    }

    @Override
    public void display() {
        clearData();
    }

    private class MyEditText extends JFormattedTextField {
        MyEditText(int x, int y) {
            this.setBounds(x, y, 250, 20);
            this.setBorder(new LineBorder(Color.GRAY, 1, true));
        }
    }

    private class MyLabel extends JLabel {
        MyLabel(String label, int x, int y, JComponent component) {
            super(label);
            this.setBounds(x, y, 130, 20);
            this.setVerticalAlignment(SwingConstants.TOP);
            this.setLabelFor(component);
        }
    }
}
