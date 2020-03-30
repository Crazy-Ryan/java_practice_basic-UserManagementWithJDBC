package com.thoughtworks.interfaces;

import com.thoughtworks.controllers.UserController;

import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    private UserController userController = new UserController();


    public void run() {
        System.out.println("1.注册");
        System.out.println("2.登录");
        System.out.println("3.退出");
        System.out.println("请输入你的选择(1~3)：");

        Scanner scanner = new Scanner(System.in);
        String mode = scanner.nextLine();
        switch (mode) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            default:
                break;
        }
    }

    public void registerHandler() {
        String registerInput = userRegisterCollector();
        if (userController.userRegister(registerInput)) {
            System.out.println(registerInput.split(",")[0] + "，恭喜你注册成功！");
        }
    }

    public String userRegisterCollector() {
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


}
