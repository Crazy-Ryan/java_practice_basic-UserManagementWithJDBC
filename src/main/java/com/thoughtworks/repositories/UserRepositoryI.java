package com.thoughtworks.repositories;

import com.thoughtworks.entities.User;

import java.util.List;

public interface UserRepositoryI {

    boolean userRegister(User user);

    User getUserByNameAndPassword(String name, String password);

}
