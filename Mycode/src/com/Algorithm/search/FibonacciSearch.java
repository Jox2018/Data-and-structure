package com.Algorithm.search;

import java.util.Arrays;

/**
 * 斐波那契查找算法
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
    }

    //因为后面我们mid=low+F(k - 1)-1,需要使用斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归的方式得到一个斐波那契数列

    /**
     * 因为后面我们mid=low+F(k - 1)-1,需要使用斐波那契数列，因此我们需要先获取到一个斐波那契数列
     * 非递归的方式得到一个斐波那契数列
     *
     * @return
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 编写斐波那契查找算法
     * 非递归的方式
     *
     * @param a   数组
     * @param key 我们需要查找的关键码
     * @return 返回对应的下标，如果没有就返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契分割值的下标
        //是斐波那契数列的长度恰好大于或者等于a的长度
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k] 的值可能大于a数组的长度，需要使用一个Arrays类，构造一个新的数组，并指向a[]
        //不足的部分会用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需要使用a数组的最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        return 1;
    }


}
