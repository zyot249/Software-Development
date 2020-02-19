/*
 * Created by JFormDesigner on Sun Dec 08 22:57:44 ICT 2019
 */

package com.group4.itss.ui.screen.saleemployee;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.border.*;

import com.group4.itss.common.AppState;
import com.group4.itss.controller.MerchandiseController;
import com.group4.itss.entity.model.Employee;
import com.group4.itss.entity.model.Merchandise;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.base.BaseScreen;
import com.group4.itss.ui.component.Breadcrumb;
import com.group4.itss.ui.component.PopUp;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component("create-merchandise-screen")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateMerchandise extends BaseScreen {
    private Navigator navigator;
    private AppState appState;

    private MerchandiseController merchandiseController;

    public CreateMerchandise(Navigator navigator,AppState appState,MerchandiseController merchandiseController) {
        this.appState = appState;
        this.navigator = navigator;
        this.merchandiseController = merchandiseController;
        initComponents();
        customize();
    }

    private void customize() {
        panel2.add(Breadcrumb.create(Arrays.asList("Home", "Create merchandise")));
    }

    private void submitHandler(MouseEvent e) {
        String name = nameField.getText();
        String unit = unitField.getText();
        String description = descriptField.getText();
        String code = codeField.getText();
        if(merchandiseController.checkValidation(name,unit,code)==false)
            return;
        if(PopUp.render(PopUp.PopupTypes.CONFIRM,"Are you sure to submit?","Confirm")==2) {
            Merchandise merchandise = new Merchandise();
            merchandise.setName(name);
            merchandise.setUnit(unit);
            merchandise.setDescription(description);
            merchandise.setCode(code);
            merchandiseController.addNewMerchandise(merchandise);
            navigator.goToHome();
            resetForm();
        }
    }

    private void resetForm() {
        nameField.setText("");
        unitField.setText("");
        descriptField.setText("");
        codeField.setText("");
    }

    private void cancelHandler(MouseEvent e) {
        if (PopUp.render(PopUp.PopupTypes.CONFIRM, "Are you sure to cancel?", "Log out") == 2)
            navigator.navigate("sale-employee-home");
    }

    private void logOutHandler(MouseEvent e) {
        navigator.onLogout();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Thanh Tung
        panel1 = new JPanel();
        saleNameLabel = new JLabel();
        ruleNameLabel = new JLabel();
        logoutBtn = new JButton();
        separator1 = new JSeparator();
        panel2 = new JPanel();
        submitBtn = new JButton();
        panel3 = new JPanel();
        label3 = new JLabel();
        nameLabel = new JLabel();
        nameField = new JTextField();
        unitLabel = new JLabel();
        unitField = new JTextField();
        descriptLable = new JLabel();
        descriptField = new JTextField();
        codeLabel = new JLabel();
        codeField = new JTextField();
        cancelBtn = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(900, 500));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder (
        0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
        . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
        red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
        beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(LineBorder.createBlackLineBorder());
            panel1.setLayout(null);

            //---- label1 ----
            saleNameLabel.setText("Vu Thanh Tung");
            saleNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(saleNameLabel);
            saleNameLabel.setBounds(420, 5, 350, 25);

            //---- label2 ----
            ruleNameLabel.setText("Sale Employee");
            ruleNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(ruleNameLabel);
            ruleNameLabel.setBounds(650, 30, 120, 25);

            //---- button1 ----
            logoutBtn.setText("Log out");
            logoutBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    logOutHandler(e);
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

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout());
        }
        add(panel2);
        panel2.setBounds(5, 75, 890, 45);

        //---- button2 ----
        submitBtn.setText("Submit");
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                submitHandler(e);
            }
        });
        add(submitBtn);
        submitBtn.setBounds(700, 425, 90, 45);

        //======== panel3 ========
        {
            panel3.setBorder(LineBorder.createBlackLineBorder());
            panel3.setLayout(null);
            panel3.add(label3);
            label3.setBounds(new Rectangle(new Point(77, 25), label3.getPreferredSize()));

            //---- label4 ----
            nameLabel.setText("Name");
            nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
            panel3.add(nameLabel);
            nameLabel.setBounds(10, 32, 120, 35);
            panel3.add(nameField);
            nameField.setBounds(160, 32, 500, 35);

            //---- label5 ----
            unitLabel.setText("Unit");
            unitLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
            panel3.add(unitLabel);
            unitLabel.setBounds(10, 140, 120, 35);
            panel3.add(unitField);
            unitField.setBounds(160, 140, 500, 35);

            //---- label6 ----
            descriptLable.setText("Description");
            descriptLable.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
            panel3.add(descriptLable);
            descriptLable.setBounds(10, 195, 120, 35);
            panel3.add(descriptField);
            descriptField.setBounds(160, 195, 500, 35);

            //---- label7 ----
            codeLabel.setText("Code");
            codeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
            panel3.add(codeLabel);
            codeLabel.setBounds(10, 85, 120, 35);
            panel3.add(codeField);
            codeField.setBounds(160, 85, 500, 35);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        add(panel3);
        panel3.setBounds(5, 140, 890, 280);

        //---- button3 ----
        cancelBtn.setText("Cancel");
        cancelBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cancelHandler(e);
            }
        });
        add(cancelBtn);
        cancelBtn.setBounds(800, 425, 90, 45);

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
    private JLabel ruleNameLabel;
    private JButton logoutBtn;
    private JSeparator separator1;
    private JPanel panel2;
    private JButton submitBtn;
    private JPanel panel3;
    private JLabel label3;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel unitLabel;
    private JTextField unitField;
    private JLabel descriptLable;
    private JTextField descriptField;
    private JLabel codeLabel;
    private JTextField codeField;
    private JButton cancelBtn;

    @Override
    public void display() {
        Employee employee = (Employee) appState.getAccount();
        saleNameLabel.setText(employee.getName());
        ruleNameLabel.setText(employee.getRole());
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
