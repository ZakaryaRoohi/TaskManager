package com.example.taskmanager.model;

import java.util.UUID;

public class User {
    private UUID mId;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
