package com.example.demo.study;

import java.util.ArrayList;

public class Arraylist {
    public static void main(String[] args) {
        ArrayList<TVshow> H = new ArrayList<>();
        TVshow one = new TVshow("工作细胞", 123);
        TVshow two = new TVshow("鬼灭之刃", 456);
        TVshow three = new TVshow("冰海战记", 789);
        TVshow four = new TVshow("小林家的妹抖龙", 135);
        TVshow five = new TVshow("某科学的超电磁炮", 246);

        H.add(one);
        H.add(two);
        H.add(three);
        H.add(four);
        H.add(five);

        for (int i = 0; i < H.size(); i++) {
            TVshow tv = H.get(i);
            System.out.println("动漫名称：" + tv.getName() + "动漫编号：" + tv.getId());
        }
    }
}
