package com.Algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 4, 5, 7, 1, 3, 6, 2}; //8 -> merge 7次  冒泡 80000 O（n^2）
        int[] temp = new int[arr.length];//归并排序需要一个额外的空间
        mergeSort(arr,0,arr.length - 1,temp);

        System.out.println("归并排序后=" + Arrays.toString(arr));*/


        //测试速度
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0-8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);


        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }


    //分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //每分解一次就合并一次
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * 合并的方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //System.out.println("╳╳╳╳");
        int i = left;//初始化i,左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列
        int t = 0;//指向temp数组的当前索引

        //（一）
        // 先把左右俩边(有序)的数据按照规则填充到temp数组中
        //直到左右俩边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序序列的当前元素，小于等于右边的有序序列的当前元素
            //即将左边的当前元素，拷贝到temp数组
            //后移t和i
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //（二）
        //把有剩余数据的一边依次填充到temp
        while (i <= mid) {
            //说明左边的有序序列还有剩余的元素，就全部填充到temp数组里
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //（三）
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;//
        //System.out.println("tempLeft=" + tempLeft + " right=" + right);
        while (tempLeft <= right) { //第一次合并 tempLeft = 0; right = 1;   第二次合并 tempLeft = 2; right = 3;
            //第三次 tempLeft = 0,right = 3    最后一次 tempLeft = 0 ,right = 7
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
