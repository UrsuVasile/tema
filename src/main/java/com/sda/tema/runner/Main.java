package com.sda.tema.runner;

import com.sda.tema.config.DIConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DIConfig.class);

        Logic logic = applicationContext.getBean(Logic.class);
        logic.action();

    }
}
