package com.how2java.service;

import com.how2java.dao.UserDao;
import com.how2java.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> queryAll() {
        return this.userDao.queryAll();
    }

    public User findUserById(int id) {
        String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        // 判断redis中是否有键值为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);

        if(hasKey) {
            User user = operations.get(key);
            logger.info("从缓存中获取数据----------： " + user.getUserName());
            return user;
        } else {
            User user = userDao.findUserById(id);
            logger.info("从数据库中获取数据----------： " + user.getUserName());
            // 写入缓存
            operations.set(key, user, 5, TimeUnit.HOURS);
            logger.info("用户信息已写入缓存----------");
            return user;
        }
    }

    /**
     * 更新用户策略：先更新数据表，成功之后，删除原来的缓存，再更新缓存
     */
    public int updateUser(User user) {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        int result = userDao.updateUser(user);
        if (result != 0) {
            String key = "user_" + user.getUid();
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                logger.info("删除缓存中的key-----------> " + key);
            }
            // 再将更新后的数据加入缓存
            User userNew = userDao.findUserById((int) user.getUid());
            if (userNew != null) {
                operations.set(key, userNew, 3, TimeUnit.HOURS);
            }
        }
        return result;
    }

    /**
     * 删除用户策略：删除数据表中数据，然后删除缓存
     */
    public int deleteUserById(int id) {
        int result = userDao.deleteUserById(id);
        String key = "user_" + id;
        if (result != 0) {
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                logger.info("删除了缓存中的key----------->:" + key);
            }
        }
        return result;
    }
}
