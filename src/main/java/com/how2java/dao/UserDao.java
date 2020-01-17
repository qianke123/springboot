package com.how2java.dao;

import com.how2java.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    @Select("Select * from user")
    List<User> queryAll();

    @Select("Select * from user where uid = #{id}")
    User findUserById(int id);

    @Update("UPDATE USER SET username = CASE WHEN (#{userName} != NULL) AND (#{userName} != '') THEN #{userName},PASSWORD = CASE WHEN (#{passWord} != NULL) AND (#{passWord} != '') THEN #{passWord},salary = CASE WHEN (#{salary} != 0) THEN #{salary} WHERE uid = #{uid}")
    int updateUser(@Param("user") User user);

    @Delete("delete from user where uid = #{id}")
    int deleteUserById(int id);
}
