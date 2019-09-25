package com.example.entities;

public class UserLog {
    private String email, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLog(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
