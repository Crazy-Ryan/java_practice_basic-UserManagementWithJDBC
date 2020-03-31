package com.thoughtworks.repositories;

import com.thoughtworks.entities.User;


public interface UserRepositoryI {

    boolean userRegister(User user);

    User getUserByNameAndPassword(String name, String password);

}
