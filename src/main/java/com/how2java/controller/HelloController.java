package com.how2java.controller;

import com.how2java.dao.UserDao;
import com.how2java.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private UserDao userDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/queryAll")
    public List getUsers() {
        List<User> list = new ArrayList<>();
        list = userDao.queryAll();
        return list;
    }
}
