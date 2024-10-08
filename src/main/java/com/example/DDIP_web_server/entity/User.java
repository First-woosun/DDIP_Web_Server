package com.example.DDIP_web_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String userid;
    private String userpwd;
    private String username;
    private String email;
    private boolean admin;

    public User() {}

    public User(String userid, String userpwd, String username, String email, boolean admin) {
        this.userid = userid;
        this.userpwd = userpwd;
        this.username = username;
        this.email = email;
        this.admin = admin;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userID) {
        this.userid = userID;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}

