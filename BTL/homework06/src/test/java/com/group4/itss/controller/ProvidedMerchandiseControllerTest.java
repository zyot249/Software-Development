package com.group4.itss.controller;

import com.group4.itss.config.AppConfig;
import com.group4.itss.entity.enumeration.ProvidedMerchandiseStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class ProvidedMerchandiseControllerTest {
    @Autowired
    ProvidedMerchandiseController merchandiseController;
    @Test
    public void testProvidedMerchandiseController(){
        assertNotNull(merchandiseController);
    }
    @Test
    public void testStatus(){
        assertNotNull(merchandiseController.getByStatus(ProvidedMerchandiseStatus.closed));
    }
}
