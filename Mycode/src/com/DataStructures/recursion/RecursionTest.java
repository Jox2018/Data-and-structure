package com.DataStructures.recursion;

public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题，回顾递归的调用机制
        test1(4);
        System.out.println("加了else后：");
        test2(4);
        System.out.println("阶乘问题");
        int res = factorial(1);
        System.out.println(res);
    }

    /**
     * 打印问题
     *
     * @param n
     */
    public static void test1(int n) {
        if (n > 2) {
            test1(n - 1);
        }
        System.out.println("n=" + n);
    }

    /**
     * 打印问题
     *
     * @param n
     */
    public static void test2(int n) {
        if (n > 2) {
            test2(n - 1);
        } else {
            System.out.println("n=" + n);
        }
    }

    /**
     * 阶乘问题
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
