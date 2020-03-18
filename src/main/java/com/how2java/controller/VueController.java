package com.how2java.controller;

import com.how2java.pojo.User;
import com.how2java.service.VueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/vue")
public class VueController {

    @Autowired
    private VueService vueService;

    @RequestMapping("/validateLogin")
    public User validateLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        return this.vueService.validateLogin(username, password);
    }
}
