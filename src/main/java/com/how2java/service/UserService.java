package com.how2java.service;

import com.how2java.dao.UserDao;
import com.how2java.pojo.User;
import com.how2java.util.FileCloudTokenUtil;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 登陆验证功能
     * @param name 用户姓名
     * @param password 密码
     * @return status :0: 不存在用户, 1: 密码错误, 2: 登陆成功
     *         fileCloudToken: 保存用户登陆信息的token
     */
    public Map<String, String> validateUserByName(String name, String password) {
        User user = null;
        Map resultMap = new HashMap<String, String>();
        user = this.userDao.findUserByName(name);
        if (user == null) {
            resultMap.put("status", "0");
        } else if (!user.getPassword().equals(password)) {
            resultMap.put("status", "1");
        } else {
            resultMap.put("status", "2");
            String fileCloudToken = FileCloudTokenUtil.getToken(name);
            resultMap.put("fileCloudToken", fileCloudToken);
            // 用户信息存入redis数据库, 并设置过期时间
            this.redisTemplate.opsForValue().set(fileCloudToken, fileCloudToken);
            this.redisTemplate.expire(fileCloudToken, 1800, TimeUnit.SECONDS);
        }
        return resultMap;
    }

    /**
     * 用户注销操作
     * @param fileCloudToken
     * @return 信息
     */
    public String userLogout(String fileCloudToken) {
        this.redisTemplate.delete(fileCloudToken);
        return "用户登陆状态已从redis中删除";
    }
}
