package com.example.demo.study;

/*
 * 1、第一个Java程序
 * 2、加深基础学习
 * 3、2020年3月30日
 * 4、\n还行符、\t制表符
 * 5、print后的信息不能换行，println后的信息能换行  
  * ？？？print语句后面跟着println的语句不显示println的信息？？？
 */
public class A {

    /**
     * print 打印方法打印出输入值，
     * println 打印方法打印出输入值并换行，看源码可知调用 println()方法调用print() ，
     *      后调用了 newLine()换行方法
     *    print语句后面跟着println的语句 println的值在print值后面，因为没有换行所以在同一行
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("NMSL!!!!\twwwwwww");
        System.out.println("淡黄色的长裙，淡黄色的头发\n---秦牛正威");
        System.out.println("我是一只小瞎子，有一天走着走着我捡到了一只钳，我去找警察，警察问我怎么了？我说我有钳了...哈哈哈");
        System.out.print("我是一只小螃蟹，有一天走着走着我的钳掉了，我去找医生，医生问我怎么了？我说我没钳了...呜呜呜");
        System.out.println("我是一只小瞎子，有一天走着走着我捡到了一只钳，我去找警察，警察问我怎么了？我说我有钳了...哈哈哈");

    }

}
