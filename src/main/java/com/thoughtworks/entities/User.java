package com.thoughtworks.entities;

public class User {
    private String id;
    private String username;
    private String phone;
    private String email;
    private String password;
    private int failedLoginCount;
    private boolean locked;

    public User() {
    }

    public User(String username, String phone, String email, String password) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User(String id, String username, String phone, String email, String password, int failedLoginCount, boolean locked) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.failedLoginCount = failedLoginCount;
        this.locked = locked;
    }


    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getFailedLoginCount() {
        return failedLoginCount;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
