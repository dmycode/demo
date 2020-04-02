package com.example.demo.study;

import java.util.Scanner;

public class StudyTest {
    public static void main(String[] args) {
        // if语句
        Scanner jer = new Scanner(System.in);

        System.out.println("请输入小吉尔的长度：");
        int a = jer.nextInt();
        System.out.println("请输入小香b的深度：");
        int b = jer.nextInt();
        if (a < b) {
            System.out.println("你不行");
        } else if (a > b) {
            System.out.println("绝顶高潮");
        } else {
            System.out.println("勉勉强强");
        }
        // for循环+嵌套循环
        for (int i = 1; i < 24; i++) {
            for (int j = 0; j < 60; j++) {

                if (i == 15) {
                    // break;循环程序执行到15时，立即终止循环
                    continue;// 跳过该次循环
                }
                System.out.println(i + "时" + j + "分");
            }
        }
        // while循环
        int c = 1;
        int sum = 0;
        while (c <= 100) {
            c++;
            if (c % 2 == 1) {
                sum += c;
            }
        }
        System.out.println("奇数和：" + sum);
    }
}
