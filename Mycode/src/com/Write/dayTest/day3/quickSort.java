package com.Write.dayTest.day3;

import java.util.Arrays;

/**
 * 快速排序
 */
public class quickSort {
    public static void main(String[] args) {
        int[] arr = {1, 10, 0, 5, 2, 11, -100};
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {

        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (l < r) {

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
            //如果交换完后发现arr[r] == pivot值，相等++ ，l后移一下
            if (arr[r] == pivot) {
                l += 1;
            }

        }

        if (l == r) {
            l += 1;
            r -= 1;
        }

        if (left < r) {
            quickSort(arr, left, r);
        }

        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
