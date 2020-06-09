package com.Write.Algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, -1, 80, 50, 30};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {


        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;
            int min = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }
}
