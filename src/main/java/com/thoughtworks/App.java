package com.thoughtworks;

import com.thoughtworks.interfaces.UserInterface;

public class App {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.run();
    }
}
