package com.group4.itss;

import com.group4.itss.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
