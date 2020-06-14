package com.Algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序
 */
public class quickSort {
    public static void main(String[] args) {
        /*int[] arr = {-9, 78, 0, 23, -567, 70};
        System.out.println("原始数组：");
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0-8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);


        quickSort(arr,0,arr.length - 1);

        //System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标     俩个指针
        int r = right;//右下标
        int pivot = arr[(left + right) / 2];//中轴值
        int temp = 0;//临时变量。作为交换时使用

        //while循环的目的是让比pivot值小的放在左边
        //比pivot大的值放在右边
        while (l < r) {
            //在pivot的左边一直找，找到一个大于或者等于pivot的值才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到一个小于或者等于pivot的值才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l >= r 说明pivot 的左右俩边的值，已经按照左边全部是
            //小于等于pivot的值，右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后发现arr[l] == pivot值，相等-- ，r前移一下
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后发现arr[r] == pivot值，相等++ ，l后移一下
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        //如果l == r,必须l++,r--,否则会栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr,left,r);
        }

        //向右递归
        if (right > l){
            quickSort(arr,l,right);
        }
    }
}
