package com.Algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1, -1, 100, -50};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0-8000000)数
        }

        System.out.println("原始数组");
        //System.out.println(Arrays.toString(arr));
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);

        System.out.println("排序后");
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {

        //在推导的过程中，已经发现规律，可以使用一个for循环解决
        //选择排序的时间复杂度是O（n^2）
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;//最小值的索引
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    //说明假定的这个不是最小的
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }

            //将最小值，放在arr[0],即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            //System.out.println("第"+ (i + 1) +"轮后~~");
            //System.out.println(Arrays.toString(arr));
        }

        /*
        //使用逐步推导的方式
        //第一轮
        //原始的数组：101，34，119，1
        //第一轮排序：1，34，119，101
        //可以把一个复杂的算法拆分成简单的问题

        //第一轮
        int minIndex = 0;//最小值的索引
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) {
                //说明假定的这个不是最小的
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }

        //将最小值，放在arr[0],即交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        System.out.println("第一轮后~~");
        System.out.println(Arrays.toString(arr));

        //第二轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) {
                min = arr[j];
                minIndex = j;
            }
        }
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第二轮后~~");
        System.out.println(Arrays.toString(arr));

        //第三轮
        minIndex = 2;
        min = arr[2];
        for (int j = 3; j < arr.length; j++) {
            if (min > arr[j]) {
                min = arr[j];
                minIndex = j;
            }
        }
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三轮后~~");
        System.out.println(Arrays.toString(arr));

         */
    }
}
