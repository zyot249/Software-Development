package com.group4.itss.ui.base;

import com.group4.itss.common.AppState;
import com.group4.itss.ui.Navigator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

public abstract class BaseScreen extends JPanel {
    @Autowired
    protected Navigator navigator;

    @Autowired
    protected AppState appState;

    public abstract void display();
}
