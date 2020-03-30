package com.thoughtworks.services;

import com.thoughtworks.entities.User;
import com.thoughtworks.repositories.UserRepository;
import com.thoughtworks.repositories.UserRepositoryI;
import org.springframework.util.DigestUtils;

import java.util.List;

public class UserService implements UserServiceI {
    private UserRepositoryI userRepository = new UserRepository();

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        return userRepository.getUserByNameAndPassword(name, passwordMd5);
    }

    @Override
    public boolean userRegister(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userRepository.userRegister(user);
    }
}
