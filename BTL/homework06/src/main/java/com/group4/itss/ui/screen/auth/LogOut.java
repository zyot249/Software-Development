package com.group4.itss.ui.screen.auth;

import com.group4.itss.ui.Navigator;
import org.springframework.beans.factory.annotation.Autowired;

public class LogOut {
    @Autowired
    private Navigator navigator;
    public LogOut(Navigator navigator){
        this.navigator = navigator;
    }
}
