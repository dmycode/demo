package com.example.demo.study;

import java.util.ArrayList;
import java.util.Iterator;

public class StudyTest {
    public static void forMethod() {
        // public static:修饰符 void:结果返回类型 forMethod():方法名称(参数类型 参数值)

        ArrayList<Integer> list = new ArrayList<Integer>();

        // Integer是一个类，是int的拓展（int的封装类）。Integer变量的初始化为null。
        // int用来进行简单的运算；Integer用来声明一个对象

        list.add(1);
        // 初始化list

        for (int i = 0; i < list.size(); i++) {
            int result = list.get(i);// 将循环的数据赋值给result
            System.out.println(result);// 输出result信息
        }
        for (Iterator<Integer> iterator = list.iterator(); iterator
                .hasNext();) {
            // .hasNext():迭代器
            int result = iterator.next();// 每有一个数据赋值给result就会返回一个元素？？？
            System.out.println(result);
        }
        for (Integer object : list) {
            int result = object;
            System.out.println(result);
        }
    }
}
