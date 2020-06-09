package com.Write.Algorithm.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 手写冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {

        int[] arr = {2, -1, -3, 50, 60, 70};
        System.out.println("原始数组");
        System.out.println(Arrays.toString(arr));
        int temp = 0;

        for (int i = 0; i < arr.length - 1 ; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if ( arr [j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" +(i + 1)+"次排序的数组");
            System.out.println(Arrays.toString(arr));
        }
    }

}

