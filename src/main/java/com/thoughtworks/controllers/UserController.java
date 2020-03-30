package com.thoughtworks.controllers;

import com.thoughtworks.entities.User;
import com.thoughtworks.services.UserService;

public class UserController {
    private UserService userService = new UserService();

    public User getUserByNameAndPassword(String input) {
        String[] inputEntries = input.split(",");
        return userService.getUserByNameAndPassword(inputEntries[0], inputEntries[1]);
    }

    public boolean userRegister(String input) {
        String[] inputEntries = input.split(",");
        return userService.userRegister(new User(inputEntries[0], inputEntries[1], inputEntries[2], inputEntries[3]));
    }
}
