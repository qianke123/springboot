package com.how2java.pojo;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

public class User implements Serializable {
    private long uid;
    private String userName;
    private int password;
    private int salary;

    public User() { }

    public User(long uid, String userName, int password, int salary) {
        this.uid = uid;
        this.userName = userName;
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
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
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
                ", userName='" + userName + '\'' +
                ", password=" + password +
                ", salary=" + salary +
                '}';
    }
}
