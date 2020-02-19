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
public class SupplierOrderLineControllerTest {
    @Autowired
    private SupplierOrderLineController supplierOrderLineController;

    @Test
    public void testControllerConfig() {
        assertNotNull(supplierOrderLineController);
    }

    @Test
    public void testGetAllLinesByOrderCode() {
        // TODO
        assertTrue(true);
    }

    @Test
    public void testGetUncompletedOrderCodes() {
        // TODO
        assertTrue(true);
    }

    @Test
    public void testUpdateOrderLines() {
        // TODO
        assertTrue(true);
    }
}
