package com.how2java.service;

import com.how2java.dao.VueDao;
import com.how2java.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VueService {

    @Autowired
    private VueDao vueDao;

    public User validateLogin(String username, String password) {
        return this.vueDao.validateLogin(username, password);
    }
}
