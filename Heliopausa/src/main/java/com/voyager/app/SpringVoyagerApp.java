package com.voyager.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringVoyagerApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringVoyagerApp.class, args);
    }


}
