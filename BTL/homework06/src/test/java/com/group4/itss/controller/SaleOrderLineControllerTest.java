package com.group4.itss.controller;

import com.group4.itss.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class SaleOrderLineControllerTest {
    @Autowired
    SaleOrderLineController saleOrderLineController;

    @Test
    public void testSubscribedOrder(){
        Assert.assertTrue(saleOrderLineController.isSubscribedOrder("SO53989","SUP00001"));
    }
}
