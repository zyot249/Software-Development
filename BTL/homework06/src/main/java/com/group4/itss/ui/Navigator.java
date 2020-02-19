package com.group4.itss.ui;

import com.group4.itss.common.AppState;
import com.group4.itss.common.Constants;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.PopUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class Navigator {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AppState appState;

    private JFrame frame = new JFrame("App");

    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.getScreenWidth(), Constants.getScreenHeight() );
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void navigate(String screenName) {
        BaseScreen screen = (BaseScreen) context.getBean(screenName);
        screen.display();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(screen);
        frame.revalidate();
        frame.repaint();
    }

    public void showPopupScreen(String popupScreenName) {
        JFrame popup = new JFrame("Popup Screen");
        popup.setSize(Constants.getPopupScreenWidth(), Constants.getPopupScreenHeight());
        popup.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        popup.setLocationRelativeTo(popup.getOwner());
        popup.setResizable(false);
        popup.setVisible(true);

        BaseScreen screen = (BaseScreen) context.getBean(popupScreenName);
        screen.display();
        popup.getContentPane().add(screen);
    }

    public void goToHome() {
        String accountRole = appState.getAccount().getRole();
        if (accountRole.equals(Constants.ROLES.SUPPLIER)) {
            navigate("supplier-home-screen");
        } else if (accountRole.equals(Constants.ROLES.STOCK_EMPLOYEE)) {
            navigate("stock-home-screen");
        } else if (accountRole.equals(Constants.ROLES.SALE_EMPLOYEE)) {
            navigate("sale-employee-home");
        } else if (accountRole.equals(Constants.ROLES.ORDER_EMPLOYEE)) {
            navigate("order-employee-home-screen");
        }
    }

    public void onLogout() {
        int option = PopUp.render(
                PopUp.PopupTypes.CONFIRM,
                Constants.MESSAGES.LOGOUT_CONFIRM_MSG,
                "Log out"
        );

        if (option == 2) {
            appState.setAccount(null);
            appState.setSelectedSaleOrderLine(null);
            appState.setCurrentSaleOrder(null);
            appState.setSaleOrderList(null);
            appState.setCurSupplierOrderList(null);
            appState.setCurSupplierOrder(null);
            navigate("login-screen");
        }
    }
}
