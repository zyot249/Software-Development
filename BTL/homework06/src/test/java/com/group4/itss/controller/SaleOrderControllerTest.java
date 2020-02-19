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
@ContextConfiguration(classes = AppConfig.class)
public class SaleOrderControllerTest {
    @Autowired
    private SaleOrderController saleOrderController;

    @Test
    public void testSaleController(){
        assertNotNull(saleOrderController);
    }

    @Test
    public void testFormat(){
        String saleOrderCode = saleOrderController.createSaleOrderCode();
        assertTrue(saleOrderCode.matches("SO\\d{5}"));
    }

    @Test
    public void testgetAllSaleOrders(){
        assertTrue(true);
    }

    @Test
    public void testCompareCode(){
        assertTrue(true);
    }

    public void testGetSaleOrderResponseByOrderCode(){
        assertTrue(true);
    }

}
