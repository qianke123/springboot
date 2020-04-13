package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableEurekaClient
// @EnableScheduling
public class Application {

    public static void main(String[] args) {
        System.out.println("hello Springboot");
        SpringApplication.run(Application.class, args);
    }
}
