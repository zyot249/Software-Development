package com.group4.itss.ui.screen.auth;

import com.group4.itss.common.Constants;
import com.group4.itss.controller.AccountController;
import com.group4.itss.entity.model.Account;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.PopUp;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component("login-screen")
public class LoginScreen extends BaseScreen {

    @Autowired
    private AccountController accountController;

    public LoginScreen() {
        initComponents();
    }

    private void onLogin(MouseEvent e) {
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if (StringUtils.isBlank(email)) {
            showErrorDialog("Warning", Constants.MESSAGES.BLANK_EMAIL_MSG);
            return;
        }

        if (StringUtils.isBlank(password)) {
            showErrorDialog("Warning", Constants.MESSAGES.BLANK_PASSWORD_MSG);
            return;
        }
        try {
            Account account = accountController.findByEmail(email);
            if (account == null) {
                showErrorDialog("Login Error", Constants.MESSAGES.NOT_FOUND_EMAIL_MSG);
                return;
            }

            if (!BCrypt.checkpw(password, account.getPassword())) {
                showErrorDialog("Login Error", Constants.MESSAGES.LOGIN_WRONG_PASSWORD_MSG);
                return;
            }

            appState.setAccount(account);
            appState.setNewAccount(null);
            navigator.goToHome();
        } catch (Exception err) {
            showErrorDialog("Error", Constants.MESSAGES.ERROR_MSG);
            err.printStackTrace();
        }
    }

    private void showErrorDialog(String title, String msg) {
        PopUp.render(PopUp.PopupTypes.ERROR, msg, title);
    }

    private void loadData() {
        lbImage.setIcon(new ImageIcon(LoginScreen.class.getResource("/image/homeimage.jpg")));
        Account newAccount = appState.getNewAccount();
        if (newAccount != null) {
            emailField.setText(newAccount.getEmail());
            passwordField.setText("");
        } else {
            passwordField.setText("");
        }
    }

    private void onSupplierSignup(MouseEvent e) {
        appState.setNewAccount(null);
        navigator.navigate("register-supplier-screen");
    }

    private void onEmployeeSignup(MouseEvent e) {
        appState.setNewAccount(null);
        navigator.navigate("employee-signup-screen");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - shyn
        lbTitle = new JLabel();
        passwordField = new JPasswordField();
        emailField = new JFormattedTextField();
        btnRegisterEmployee = new JButton();
        btnRegisterSupplier = new JButton();
        separator1 = new JSeparator();
        btnLogin = new JButton();
        pnImage = new JPanel();
        lbImage = new JLabel();

        //======== this ========
        setBackground(UIManager.getColor("Button.background"));
        setLayout(null);

        //---- lbTitle ----
        lbTitle.setText("<html>Overseas Import/Export <br>IT System</html>");
        lbTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        add(lbTitle);
        lbTitle.setBounds(500, 20, 400, 160);

        //---- passwordField ----
        passwordField.setBorder(new TitledBorder(new LineBorder(Color.gray, 2, true), " Password: ", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
        passwordField.setBackground(UIManager.getColor("Button.background"));
        add(passwordField);
        passwordField.setBounds(550, 260, 300, 50);

        //---- emailField ----
        emailField.setBorder(new TitledBorder(new LineBorder(Color.gray, 2, true), " Email: ", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
        emailField.setBackground(UIManager.getColor("Button.background"));
        add(emailField);
        emailField.setBounds(550, 200, 300, 50);

        //---- btnRegisterEmployee ----
        btnRegisterEmployee.setText("Register as a EMPLOYEE");
        btnRegisterEmployee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onEmployeeSignup(e);
            }
        });
        add(btnRegisterEmployee);
        btnRegisterEmployee.setBounds(600, 390, 200, 30);

        //---- btnRegisterSupplier ----
        btnRegisterSupplier.setText("Register as a SUPPLIER");
        btnRegisterSupplier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onSupplierSignup(e);
            }
        });
        add(btnRegisterSupplier);
        btnRegisterSupplier.setBounds(600, 415, 200, 30);
        add(separator1);
        separator1.setBounds(600, 370, 200, 10);

        //---- btnLogin ----
        btnLogin.setText("SIGN IN");
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onLogin(e);
            }
        });
        add(btnLogin);
        btnLogin.setBounds(600, 340, 200, 30);

        //======== pnImage ========
        {
            pnImage.setLayout(null);
            pnImage.add(lbImage);
            lbImage.setBounds(0, 0, 500, 500);
        }
        add(pnImage);
        pnImage.setBounds(0, 0, 500, 500);

        setPreferredSize(new Dimension(900, 500));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - shyn
    private JLabel lbTitle;
    private JPasswordField passwordField;
    private JFormattedTextField emailField;
    private JButton btnRegisterEmployee;
    private JButton btnRegisterSupplier;
    private JSeparator separator1;
    private JButton btnLogin;
    private JPanel pnImage;
    private JLabel lbImage;

    @Override
    public void display() {
        loadData();
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
