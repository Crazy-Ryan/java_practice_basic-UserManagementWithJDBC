package com.thoughtworks.interfaces;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.controllers.UserController;
import com.thoughtworks.entities.User;

import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    private UserController userController = new UserController();

    public void run() {
        boolean exitFlag = false;
        Scanner scanner = new Scanner(System.in);
        while (!exitFlag) {
            System.out.println("1.注册");
            System.out.println("2.登录");
            System.out.println("3.退出");
            System.out.println("请输入你的选择(1~3)：");
            switch (scanner.nextLine()) {
                case "1":
                    registerHandler();
                    break;
                case "2":
                    loginHandler();
                    break;
                case "3":
                    exitFlag = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void registerHandler() {
        String registerInput = userRegisterCollector();
        if (Boolean.toString(true).equals(userController.userRegister(registerInput))) {
            System.out.println(registerInput.split(",")[0] + "，恭喜你注册成功！");
        }
    }

    private void loginHandler() {
        boolean isFinished = false;
        while (!isFinished) {
            JSONObject loginJO = JSON.parseObject( userController.getUserByNameAndPassword(userLoginCollector()));
            User loginUser = new User(
                    loginJO.getInteger("id"),
                    loginJO.getString("username"),
                    loginJO.getString("phone"),
                    loginJO.getString("email"),
                    loginJO.getString("password"),
                    loginJO.getInteger("failedLoginCount"),
                    loginJO.getBoolean("locked"));
            if (loginUser.isLocked()) {
                System.out.println("您已3次输错密码，账号被锁定");
                isFinished = true;
            } else {
                if (null == loginUser.getUsername()) {
                    System.out.println("密码或用户名错误");
                    System.out.println("请重新输入用户名和密码：");
                } else {
                    System.out.println(loginUser.getUsername() + "，欢迎回来！");
                    System.out.println("您的手机号是" + loginUser.getPhone() + "，邮箱是" + loginUser.getEmail());
                    isFinished = true;
                }
            }
        }
    }

    private String userRegisterCollector() {
        System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码):");
        Scanner scanner = new Scanner(System.in);
        boolean isInputFormatCorrect = false;
        boolean isEntitiesFormatCorrect = false;
        String registerInput = "";
        while (!(isInputFormatCorrect && isEntitiesFormatCorrect)) {
            registerInput = scanner.nextLine();
            if (FormatCheckUtil.registerFormatCheck(registerInput)) {
                isInputFormatCorrect = true;
                isEntitiesFormatCorrect = true;
                Map<String, Boolean> entitiesFormatCheckResult = FormatCheckUtil.registerEntitiesFormatCheck(registerInput);
                for (String entity : entitiesFormatCheckResult.keySet()) {
                    if (!entitiesFormatCheckResult.get(entity)) {
                        System.out.println(FormatCheckUtil.entityTranslator.get(entity) + "不合法");
                        isEntitiesFormatCorrect = false;
                    }
                }
                if (!isEntitiesFormatCorrect) {
                    System.out.println("请按正确格式输入注册信息：");
                }
            } else {
                System.out.println("格式错误");
                System.out.println("请按正确格式输入注册信息：");
            }
        }
        return registerInput;
    }

    private String userLoginCollector() {
        System.out.println("请输入用户名和密码(格式：用户名,密码)：");
        Scanner scanner = new Scanner(System.in);
        boolean isInputFormatCorrect = false;
        String loginInput = "";
        while (!isInputFormatCorrect) {
            loginInput = scanner.nextLine();
            if (FormatCheckUtil.loginFormatCheck(loginInput)) {
                isInputFormatCorrect = true;
            } else {
                System.out.println("格式错误");
                System.out.println("请按正确格式输入用户名和密码：");
            }
        }
        return loginInput;
    }
}
