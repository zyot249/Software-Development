package com.group4.itss.ui.screen.auth;

import com.group4.itss.common.Constants;
import com.group4.itss.controller.AccountController;
import com.group4.itss.controller.EmployeeController;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.JFormattedDateTextField;
import com.group4.itss.ui.component.PopUp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Component("employee-signup-screen")
public class EmployeeSignupScreen extends BaseScreen {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private AccountController accountController;

    public EmployeeSignupScreen() {
        initComponents();
        rbtnMale.setSelected(true);
    }

    private void clearData() {
        txtfDOB.setText("");
        txtfConfirmPassword.setText("");
        txtfEmail.setText("");
        txtfName.setText("");
        txtfPassword.setText("");
        cbAgreeTerm.setSelected(false);
        btnSignUp.setEnabled(false);
    }

    private void onBtnCancelActionPerformed(ActionEvent e) {
        showConfirmDialog("Cancel", Constants.MESSAGES.CANCEL_SIGN_UP_CONFIRM_MSG);
    }

    private void onBtnSignupActionPerformed(ActionEvent e) {
        String email = txtfEmail.getText();
        String password = txtfPassword.getText();
        String cfPassword = txtfConfirmPassword.getText();
        String name = txtfName.getText();
        if (validateInput(email, password, cfPassword, name)) {
            showConfirmDialog("Submit", Constants.MESSAGES.SUBMIT_CONFIRM_MSG);
        }
    }

    private boolean validateInput(String email, String password, String cfPassword, String name) {

        if (StringUtils.isBlank(cfPassword)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_CONFIRM_PASSWORD_MSG);
            return false;
        } else {
            if (!cfPassword.equals(password)) {
                displayErrorMessage(Constants.MESSAGES.WRONG_CONFIRM_PASSWORD_MSG);
                return false;
            }
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

        if (StringUtils.isBlank(email)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_EMAIL_MSG);
            return false;
        }

        if (!email.matches(Constants.EMAIL_PATTERN)) {
            displayErrorMessage(Constants.MESSAGES.WRONG_EMAIL_MSG);
            return false;
        }

        if (StringUtils.isBlank(name)) {
            displayErrorMessage(Constants.MESSAGES.BLANK_NAME_MSG);
            return false;
        }

        try {
            txtfDOB.toDate(txtfDOB.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            displayErrorMessage(Constants.MESSAGES.WRONG_DOB_MSG);
            return false;
        }

        displayErrorMessage("");
        return true;
    }

    private void onCbAgreeTermActionPerformed(ActionEvent e) {
        if (cbAgreeTerm.isSelected()) {
            btnSignUp.setEnabled(true);
        } else {
            btnSignUp.setEnabled(false);
        }
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
                registerEmployee();
            }
        }
    }

    private void registerEmployee() {
        try {
            if (accountController.isExistedEmail(txtfEmail.getText())) {
                displayErrorMessage(Constants.MESSAGES.EXISTED_EMAIL_MSG);
            } else {
                String email = txtfEmail.getText();
                String password = txtfPassword.getText();
                String name = txtfName.getText();
                Date dob = txtfDOB.toDate(txtfDOB.getText());
                int gender = rbtnMale.isSelected()? Constants.GENDER_MALE : Constants.GENDER_FEMALE;
                String system = "";
                switch (Objects.requireNonNull(cmboxSystem.getSelectedItem()).toString()) {
                    case "Sale": {
                        system = Constants.ROLES.SALE_EMPLOYEE;
                        break;
                    }
                    case "Stock": {
                        system = Constants.ROLES.STOCK_EMPLOYEE;
                        break;
                    }
                    case "Order": {
                        system = Constants.ROLES.ORDER_EMPLOYEE;
                        break;
                    }
                }
                Employee employee = new Employee(
                    email,
                    password,
                    system,
                    name,
                    dob,
                    gender
                );
                employeeController.createEmployee(employee);
                showSuccessDialog("Success", Constants.MESSAGES.REGISTER_SUCCESS_MSG);
                appState.setNewAccount(employee);
                close();
            }
        } catch (Exception e) {
            displayErrorMessage(Constants.MESSAGES.ERROR_MSG);
        }
    }

    private void close() {
        navigator.navigate("login-screen");
    }

    private void showSuccessDialog(String title, String msg) {
        PopUp.render(PopUp.PopupTypes.SUCCESS, msg, title);
    }

    private void onRbtnFemaleActionPerformed(ActionEvent e) {
        if (rbtnFemale.isSelected()) {
            rbtnMale.setSelected(false);
        } else {
            rbtnMale.setSelected(true);
        }
    }

    private void onRbtnMaleActionPerformed(ActionEvent e) {
        if (rbtnMale.isSelected()) {
            rbtnFemale.setSelected(false);
        } else {
            rbtnFemale.setSelected(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - shyn
        title = new JLabel();
        lbEmail = new JLabel();
        lbPassword = new JLabel();
        lbDOB = new JLabel();
        lbGender = new JLabel();
        txtfEmail = new JTextField();
        txtfPassword = new JPasswordField();
        rbtnMale = new JRadioButton();
        rbtnFemale = new JRadioButton();
        txtfDOB = new JFormattedDateTextField();
        lbConfirmPassword = new JLabel();
        txtfConfirmPassword = new JPasswordField();
        btnCancel = new JButton();
        btnSignUp = new JButton();
        lbErrorMessage = new JLabel();
        cbAgreeTerm = new JCheckBox();
        lbSystem = new JLabel();
        cmboxSystem = new JComboBox<>();
        lbName = new JLabel();
        txtfName = new JTextField();

        //======== this ========
        setLayout(null);

        //---- title ----
        title.setText("Employee Signup");
        title.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);
        title.setBounds(315, 10, 270, 70);

        //---- lbEmail ----
        lbEmail.setText("Email*:");
        add(lbEmail);
        lbEmail.setBounds(150, 90, 130, 30);

        //---- lbPassword ----
        lbPassword.setText("Password*:");
        add(lbPassword);
        lbPassword.setBounds(150, 140, 130, 30);

        //---- lbDOB ----
        lbDOB.setText("DOB:");
        lbDOB.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbDOB);
        lbDOB.setBounds(480, 290, 100, 30);

        //---- lbGender ----
        lbGender.setText("Gender*:");
        add(lbGender);
        lbGender.setBounds(150, 290, 130, 30);

        //---- txtfEmail ----
        txtfEmail.setBorder(new LineBorder(Color.gray, 1, true));
        add(txtfEmail);
        txtfEmail.setBounds(280, 90, 470, 30);

        //---- txtfPassword ----
        txtfPassword.setBorder(new LineBorder(Color.gray, 1, true));
        add(txtfPassword);
        txtfPassword.setBounds(280, 140, 470, 30);

        //---- rbtnMale ----
        rbtnMale.setText("Male");
        rbtnMale.addActionListener(this::onRbtnMaleActionPerformed);
        add(rbtnMale);
        rbtnMale.setBounds(280, 290, 100, 30);

        //---- rbtnFemale ----
        rbtnFemale.setText("Female");
        rbtnFemale.addActionListener(this::onRbtnFemaleActionPerformed);
        add(rbtnFemale);
        rbtnFemale.setBounds(380, 290, 100, 30);

        //---- txtfDOB ----
        txtfDOB.setBorder(new LineBorder(Color.gray, 1, true));
        add(txtfDOB);
        txtfDOB.setBounds(580, 290, 170, 30);

        //---- lbConfirmPassword ----
        lbConfirmPassword.setText("Confirm password*:");
        add(lbConfirmPassword);
        lbConfirmPassword.setBounds(150, 190, 130, 30);

        //---- txtfConfirmPassword ----
        txtfConfirmPassword.setBorder(new LineBorder(Color.gray, 1, true));
        add(txtfConfirmPassword);
        txtfConfirmPassword.setBounds(280, 190, 470, 30);

        //---- btnCancel ----
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(this::onBtnCancelActionPerformed);
        add(btnCancel);
        btnCancel.setBounds(235, 440, 180, 33);

        //---- btnSignUp ----
        btnSignUp.setText("Sign up");
        btnSignUp.setEnabled(false);
        btnSignUp.addActionListener(this::onBtnSignupActionPerformed);
        add(btnSignUp);
        btnSignUp.setBounds(485, 440, 180, 33);

        //---- lbErrorMessage ----
        lbErrorMessage.setText("text");
        lbErrorMessage.setForeground(Color.red);
        lbErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lbErrorMessage.setVisible(false);
        add(lbErrorMessage);
        lbErrorMessage.setBounds(150, 380, 600, 25);

        //---- cbAgreeTerm ----
        cbAgreeTerm.setText("I agree the terms of the agreement");
        cbAgreeTerm.addActionListener(this::onCbAgreeTermActionPerformed);
        add(cbAgreeTerm);
        cbAgreeTerm.setBounds(308, 412, 285, 20);

        //---- lbSystem ----
        lbSystem.setText("System*:");
        add(lbSystem);
        lbSystem.setBounds(150, 340, 130, 30);

        //---- cmboxSystem ----
        cmboxSystem.setModel(new DefaultComboBoxModel<>(Constants.EMPLOYEE_SYSTEMS));
        add(cmboxSystem);
        cmboxSystem.setBounds(280, 340, 200, 30);

        //---- lbName ----
        lbName.setText("Name*:");
        add(lbName);
        lbName.setBounds(150, 240, 130, 30);

        //---- txtfName ----
        txtfName.setBorder(new LineBorder(Color.gray, 1, true));
        add(txtfName);
        txtfName.setBounds(280, 240, 470, 30);

        setPreferredSize(new Dimension(900, 500));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - shyn
    private JLabel title;
    private JLabel lbEmail;
    private JLabel lbPassword;
    private JLabel lbDOB;
    private JLabel lbGender;
    private JTextField txtfEmail;
    private JPasswordField txtfPassword;
    private JRadioButton rbtnMale;
    private JRadioButton rbtnFemale;
    private JFormattedDateTextField txtfDOB;
    private JLabel lbConfirmPassword;
    private JPasswordField txtfConfirmPassword;
    private JButton btnCancel;
    private JButton btnSignUp;
    private JLabel lbErrorMessage;
    private JCheckBox cbAgreeTerm;
    private JLabel lbSystem;
    private JComboBox<String> cmboxSystem;
    private JLabel lbName;
    private JTextField txtfName;

    @Override
    public void display() {
        clearData();
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
