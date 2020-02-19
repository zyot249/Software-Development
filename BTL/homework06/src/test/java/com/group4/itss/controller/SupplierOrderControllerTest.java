package com.group4.itss.controller;

import com.group4.itss.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class SupplierOrderControllerTest {
    @Autowired
    private SupplierOrderController supplierOrderController;

    @Test
    public void testControllerConfig() {
        assertNotNull(supplierOrderController);
    }

    @Test
    public void testGetNextSupplierOrderCode() {
        String orderCode = supplierOrderController.getNextSupplierOrderCode();
        assertTrue(orderCode.matches("SUPORDER\\d{5}"));
    }

    @Test
    public void getOrderByOrderCodeList() {
        // TODO
        assertTrue(true);
    }

    @Test
    public void testGetAllOrder() {
        // TODO
        assertTrue(true);
    }
}
