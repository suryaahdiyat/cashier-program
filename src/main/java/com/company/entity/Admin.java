package com.company.entity;

import java.sql.Timestamp;

public class Admin {

    private String number;

    private String userName;

    private String password;

    private String level;

    private Timestamp was_added;

    public Timestamp getWas_added() {
        return was_added;
    }

    public void setWas_added(Timestamp was_added) {
        this.was_added = was_added;
    }

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Admin(String userName, String password, String level) {
        this.level = level;
        this.userName = userName;
        this.password = password;
    }

    public Admin(String number, String userName, String password, String level, Timestamp was_added) {
        this.number = number;
        this.userName = userName;
        this.password = password;
        this.level = level;
        this.was_added = was_added;
    }

    public Admin(String userName) {
        this.userName = userName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
