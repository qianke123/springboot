package com.how2java.pojo;

import java.io.Serializable;
import java.util.Date;

public class Folder implements Serializable {
    private String userName;
    private String folderName;
    // 是否是文件夹, 0: 不是, 1: 是
    private int folderBool;
    private Date date;
    // 权限, 0: 私有, 1: 可分享
    private int authority;
    private String folderUrl;

    public Folder() {
    }

    public Folder(String userName, String folderName, int folderBool, Date date, int authority, String folderUrl) {
        this.userName = userName;
        this.folderName = folderName;
        this.folderBool = folderBool;
        this.date = date;
        this.authority = authority;
        this.folderUrl = folderUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getFolderBool() {
        return folderBool;
    }

    public void setFolderBool(int folderBool) {
        this.folderBool = folderBool;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getFolderUrl() {
        return folderUrl;
    }

    public void setFolderUrl(String folderUrl) {
        this.folderUrl = folderUrl;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "userName='" + userName + '\'' +
                ", folderName='" + folderName + '\'' +
                ", folderBool=" + folderBool +
                ", date=" + date +
                ", authority=" + authority +
                ", folderUrl='" + folderUrl + '\'' +
                '}';
    }
}
