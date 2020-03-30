## MySQL 建库建表语句

```
CREATE DATABASE tw_userManagement DEFAULT CHARSET UTF8MB4 COLLATE UTF8MB4_GENERAL_CI;
use tw_userManagement;
CREATE TABLE user_info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(40) NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(100),
    password CHAR(32)
)  DEFAULT CHARSET UTF8MB4;

alter table user_info add column locked Boolean default false;
alter table user_info add column failed_login_count int default 0;
```

# 账号管理-命令行版

## 功能描述

我们现在做一个命令行应用。当程序启动时，我们会看到一个命令行界面：

```
1. 注册
2. 登录
3. 退出
请输入你的选择(1~3)：
```

如果我们输入1，那么界面就会变成：

```
请输入注册信息(格式：用户名,手机号,邮箱,密码)：
```

如果输入格式不正确，就返回：

```
格式错误
请按正确格式输入注册信息：
```

用户名必须是2~10个任意字符
手机号必须是1开头的11位数字
邮箱必须包含@
密码必须是8~16个字符，含有至少一个数字和一个字母
例如用户名不符合要求，就返回

```
用户名不合法
请输入合法的注册信息：
```

如果输入格式正确，就返回

```
xxx，恭喜你注册成功！
```

然后打印

```
1. 注册
2. 登录
3. 退出
请输入你的选择(1~3)：
```

等于回到了主界面。 如果我们在主界面输入了2，那么界面就会变成：

```
请输入用户名和密码(格式：用户名,密码)：
```

如果输入格式不正确，就会打印：

```
格式错误
请按正确格式输入用户名和密码：
```

如果输入的用户名不存在或密码不正确，则会打印：

```
密码或用户名错误
请重新输入用户名和密码：
```

如果连续3次密码错误，则账号被锁定，即使密码正确也将无法登陆，打印：

```
您已3次输错密码，账号被锁定
```

然后回到主界面

如果输入正确，则会打印：

```
xxx，欢迎回来！
您的手机号是xxx，邮箱是xxx
```

然后回到主界面

## 作业要求

1. 用数据库存储数据
2. 密码错误次数和锁定状态通过数据库来维护