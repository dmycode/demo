package com.example.demo.study;

import java.util.Scanner;

public class shopping {
    public static void main(String[] args) {
        String Name = ("admin");
        String password = ("123456");

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名：");
            String inputname = input.next();
            System.out.println("请输入密码：");
            String inputpassword = input.next();
            if (inputname.equalsIgnoreCase(Name)
                    && inputpassword.equalsIgnoreCase(password)) {
                System.out.println("登录成功，欢迎使用购物平台");
                break;
            } else {
                System.out.println("登录次数还剩" + (3 - i) + "次");
            }
            if (i == 3) {
                System.out.println("登陆次数耗尽，你的账号已被冻结");
            }

        }

    }
}
