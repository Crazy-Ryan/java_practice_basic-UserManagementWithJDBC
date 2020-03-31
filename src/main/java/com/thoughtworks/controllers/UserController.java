package com.thoughtworks.controllers;

import com.thoughtworks.entities.User;
import com.thoughtworks.services.UserService;
import com.alibaba.fastjson.JSON;

public class UserController {
    private UserService userService = new UserService();

    public String getUserByNameAndPassword(String input) {
        String[] inputEntries = input.split(",");
        return JSON.toJSONString(userService.getUserByNameAndPassword(inputEntries[0], inputEntries[1]));
    }

    public String userRegister(String input) {
        String[] inputEntries = input.split(",");
        return Boolean.toString(userService.userRegister(new User(inputEntries[0], inputEntries[1], inputEntries[2], inputEntries[3])));
    }
}
