package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// @EnableEurekaClient
// @EnableScheduling
public class Application {

    public static void main(String[] args) {
        System.out.println("hello Springboot");
        SpringApplication.run(Application.class, args);
    }
}
