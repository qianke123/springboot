package com.how2java.pojo;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

public class User implements Serializable {
    private long uid;
    private String username;
    private String password;
    private int salary;

    public User() { }

    public User(long uid, String userName, String password, int salary) {
        this.uid = uid;
        this.username = userName;
        this.password = password;
        this.salary = salary;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + username + '\'' +
                ", password=" + password +
                ", salary=" + salary +
                '}';
    }
}
