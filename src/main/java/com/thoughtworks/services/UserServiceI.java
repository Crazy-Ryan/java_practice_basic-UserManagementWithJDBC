package com.thoughtworks.services;

import com.thoughtworks.entities.User;


public interface UserServiceI {
    boolean userRegister(User user);

    User getUserByNameAndPassword(String name, String password);

}
