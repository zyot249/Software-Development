package com.group4.itss.controller;

import com.group4.itss.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Test
    public void testControllerConfig() {
        assertNotNull(accountController);
    }

    @Test
    public void testCaseExistedEmail() {
        String email = "stock@gmail.com";
        assertTrue(accountController.isExistedEmail(email));
    }

    @Test
    public void testCaseAvailableEmail() {
        String email = "abcxzy@gmail.com";
        assertFalse(accountController.isExistedEmail(email));
    }

    @Test
    public void testCaseFoundAccount() {
        String email = "stock@gmail.com";
        assertNotNull(accountController.findByEmail(email));
    }

    @Test
    public void testCaseNotFoundAccount() {
        String email = "abcxzy@gmail.com";
        assertNull(accountController.findByEmail(email));
    }
}
