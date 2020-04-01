package com.example.demo.study;

import java.util.Scanner;

/*
 * 三元运算符
 * （操作数）？值1：值2：
 * 参数是一个数字，
 * 如果这个大于100，返回“正确”，否则返回“错误”
 */
public class StudyTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int a = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数值：");
        a = input.nextInt();
        System.out.println(a > 100 ? true : false);
    }
}
