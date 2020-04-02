package com.example.demo.study;

import java.util.Scanner;

/*
 * 三元运算符
 * （操作数）？值1：值2：
 * 参数是一个数字，
 * 如果这个大于100，返回“正确”，否则返回“错误”
 * 写一个方法，用三元运算符，参数是一个数字，如果这个大于100，返回“正确”，否则返回“错误”
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

    /**
     * 
        * @Title: test
        * @Description:不要写main方法，这个方法在一个项目中除了测试类，应该只有一个
        * 返回是"正确"或"错误",不是true或false
        * @param arg
        * @return 
        * String 返回类型
        * @author dy
    	* @date 2020年4月2日
        * @throws
     */
    public String test(int arg) {
        String result = "";
        result = arg > 100 ? "正确" : "错误";
        return result;
    };
}
