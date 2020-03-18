package com.how2java.dao;

import com.how2java.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VueDao {
    @Select("SELECT * FROM test.user where user.username = #{username} and user.password = #{password}")
    User validateLogin(@Param("username") String username, @Param("password") String password);
}
