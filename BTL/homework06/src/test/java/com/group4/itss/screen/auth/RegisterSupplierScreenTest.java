package com.group4.itss.screen.auth;

import com.group4.itss.config.AppConfig;
import com.group4.itss.ui.screen.auth.RegisterSupplierScreen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class RegisterSupplierScreenTest {

    @Test
    public void testValidateInputValidInput() {
        RegisterSupplierScreen registerSupplierScreen = new RegisterSupplierScreen();
        String email = "mail@gmail.com";
        String password = "12345678";
        String cfPassword = "12345678";
        String name = "name";
        String phone = "+84347818811";

        assertTrue(registerSupplierScreen.validateInput(email, password, cfPassword, name, phone));
    }

    @Test
    public void testValidateInputWrongEmail() {
        RegisterSupplierScreen registerSupplierScreen = new RegisterSupplierScreen();
        String email = "mailgmail.com";
        String password = "12345678";
        String cfPassword = "12345678";
        String name = "name";
        String phone = "+84347818811";

        assertFalse(registerSupplierScreen.validateInput(email, password, cfPassword, name, phone));
    }

    @Test
    public void testValidateInputWrongPassword() {
        RegisterSupplierScreen registerSupplierScreen = new RegisterSupplierScreen();
        String email = "mail@gmail.com";
        String password = "1234567";
        String cfPassword = "12345678";
        String name = "name";
        String phone = "+84347818811";

        assertFalse(registerSupplierScreen.validateInput(email, password, cfPassword, name, phone));
    }

    @Test
    public void testValidateInputWrongCfPassword() {
        RegisterSupplierScreen registerSupplierScreen = new RegisterSupplierScreen();
        String email = "mail@gmail.com";
        String password = "12345678";
        String cfPassword = "12345677";
        String name = "name";
        String phone = "+84347818811";

        assertFalse(registerSupplierScreen.validateInput(email, password, cfPassword, name, phone));
    }

    @Test
    public void testValidateInputWrongPhone() {
        RegisterSupplierScreen registerSupplierScreen = new RegisterSupplierScreen();
        String email = "mail@gmail.com";
        String password = "12345678";
        String cfPassword = "12345678";
        String name = "name";
        String phone = "+843a7818811";

        assertFalse(registerSupplierScreen.validateInput(email, password, cfPassword, name, phone));
    }
}
