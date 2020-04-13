package com.how2java.controller;

import com.how2java.pojo.User;
import com.how2java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 返回用户信息
     * @param name 姓名
     * @return 用户对象
     */
    @RequestMapping("/getUserInfo")
    public User getUserInfo(@RequestParam("username") String name) {
        return this.userService.getUserInfo(name);
    }

    /**
     * 登陆验证功能
     * @param name 用户姓名
     * @param password 密码
     * @return 0: 不存在用户, 1: 密码错误, 2: 登陆成功
     */
    @RequestMapping("/validateUserByName")
    public Map<String, String> validateUserByName(@RequestParam("username") String name,
                                                  @RequestParam("password") String password) {
        return this.userService.validateUserByName(name, password);
    }

    /**
     * 用户注销
     * @param fileCloudToken
     * @return 删除成功信息
     */
    @RequestMapping("/userLogout")
    public String userLogout(@RequestParam("fileCloudToken") String fileCloudToken) {
        return this.userService.userLogout(fileCloudToken);
    }

}
