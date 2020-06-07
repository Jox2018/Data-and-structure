package com.DataStructures.recursion;

public class Queue8 {

    //先定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];

    public static void main(String[] args) {

    }

    /**
     * 查看当我们放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
     * 规则：不能同一行，同一列，同一斜线
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //说明
            //1. array[i] == array[n] 表示判断第n个皇后是否和前面的n-1在同一列
            //2. Math.abs(n - i) == Math.abs(array[n - array[i]]) 表示判断第n个皇后是否和第i个皇后在同一斜线
            // 第二个皇后 n = 1 放在第二列 1 ，array[1] = 1
            //abs(1 - 0) == 1   abs(array[1] - array[0])=abs(1 - 0) == 1
            //n代表第几个皇后,  斜率的概念好理解一点
            //3. 判断是否在同一行没有必要，n每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 这个方法可以将皇后摆放的位置打印出来
     */
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
