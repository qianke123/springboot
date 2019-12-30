package com.how2java.controller;

import com.how2java.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @RequestMapping("/users")
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "qianke", 22));
        list.add(new User(2,"lisi", 33));
        list.add(new User(3, "wangwu", 40));
        return list;
    }
}
