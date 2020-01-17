package com.how2java.controller;

import com.how2java.dao.UserDao;
import com.how2java.pojo.User;
import com.how2java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/queryAll")
    public List getUsers() {
        return userService.queryAll();
    }

    @RequestMapping("/findUserById")
    public User findUserById(@RequestParam("id") int id) {
        return userService.findUserById(id);
    }

    @RequestMapping("/updateUser")
    public String updateUser() {
        User user = new User();
        user.setUid(1);
        user.setUserName("cat");
        user.setPassword("miaomiao");
        user.setSalary(40000);
        int result = this.userService.updateUser(user);

        if(result != 0) {
            return "update user success!";
        }

        return "fail to update user!";
    }

    @RequestMapping("/deleteUserById")
    public String deleteUserById(int id) {
        int result = this.userService.deleteUserById(id);
        if(result != 0) {
            return "delete user success!";
        }

        return "fail to delete user";
    }
}
