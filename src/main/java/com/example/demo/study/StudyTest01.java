package com.example.demo.study;

/*4月08日
 * 方法
 */
public class StudyTest01 {
    public static void main(String[] args) {
        int num = sum(10);
        System.out.println(num);
        int i = 0;
        System.out.println("=========·数组遍历·===========");
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        MethodViod(array);
        System.out.println("=========·数组遍历II·===========");
        int[] array01 = { 11, 22, 33, 44, 55, 66 };
        printArray(array01);
    }

    public static int sum(int a) {
        int result = a;
        return result;// 有返回值
    }

    public static void MethodViod(int[] array) {
        // 无返回值，省略return或者直接没有。固定关键字：void 直接调用
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void printArray(int[] array01) {
        int sum = 0;
        for (int i = 0; i < array01.length; i++) {
            sum += array01[i];
        }
        System.out.println("总和" + sum);
    }
}
