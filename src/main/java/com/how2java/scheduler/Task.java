package com.how2java.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

    // @Scheduled(fixedRate = 10000)
    public void test() {
        System.out.println("hello");
    }

}
