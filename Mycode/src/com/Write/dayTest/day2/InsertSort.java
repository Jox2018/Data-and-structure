package com.Write.dayTest.day2;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 4, 1, 7, 10, 100, 11, 6};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            if (insertIndex != i - 1) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
