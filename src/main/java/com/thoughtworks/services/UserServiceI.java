package com.thoughtworks.services;

import com.thoughtworks.entities.User;

import java.util.List;

public interface UserServiceI {
    boolean userRegister(User user);

    User getUserByNameAndPassword(String name, String password);

}
