package com.example.taskmanager.model;

import java.util.UUID;

public class User {
    private UUID mId;
    private String username;
    private String password;
    private boolean role;
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password , boolean role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
