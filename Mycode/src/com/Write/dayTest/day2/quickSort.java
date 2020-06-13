package com.Write.dayTest.day2;

import java.util.Arrays;

/**
 * 快速排序
 */
public class quickSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 0, -1, 100, -20, 30};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {

        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (r > l) {

            while (arr[l] < pivot) {
                l += 1;
            }

            while (arr[r] > pivot) {
                r -= 1;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r -= 1;
            }

            if (arr[r] == pivot) {
                l += 1;
            }
        }

        if (l == r) {
            l += 1;
            r -= 1;
        }

        if (l < right) {
            quickSort(arr, l, right);
        }

        if (r > left) {
            quickSort(arr, left, r);
        }
    }
}
