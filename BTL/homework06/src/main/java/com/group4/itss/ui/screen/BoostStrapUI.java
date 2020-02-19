package com.group4.itss.ui.screen;

import com.group4.itss.ui.Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BoostStrapUI {
    @Autowired
    Navigator navigator;

    @PostConstruct
    public void render() {
        navigator.navigate("login-screen");
        navigator.display();
    }
}
