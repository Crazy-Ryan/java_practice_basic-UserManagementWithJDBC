package com.thoughtworks;

import com.thoughtworks.interfaces.UserInterface;

public class App {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
//        userController.userRegister("yong,shou,you,mi");
//        User result = userController.getUserByNameAndPassword("yong,mi");
            userInterface.registerHandler();

            System.out.println(1);
    }
}
