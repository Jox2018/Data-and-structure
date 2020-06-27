package com.Write.Algorithm.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {

        int[] arr = {8, 2, 3, 4, 1, 9};
        sortHeap(arr);

    }

    public static void sortHeap(int[] arr){

        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0;j--){
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr    要调整的数组
     * @param i      非叶子节点在数组中的索引
     * @param length 要调整的数组长度
     */
    public static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i];

        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }

            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }

        arr[i] = temp;
    }
}
