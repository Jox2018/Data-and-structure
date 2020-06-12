package com.Algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0-8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        //shellSort(arr);//交换
        shellSort2(arr);//移位

        //System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }

    //使用逐步推导的方式
    //交换式
    public static void shellSort(int[] arr) {

        //根据前面的逐步分析，使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            int temp = 0;
            //希尔排序的第一轮排序
            //因为第一轮排序，是将10个数据分成了5组
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共5组，每组有2个元素），步长是5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前的元素大于加上步长的那个元素，说明需要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }

        /*

        交换法

        int temp = 0;
        //希尔排序的第一轮排序
        //因为第一轮排序，是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，每组有2个元素），步长是5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前的元素大于加上步长的那个元素，说明需要交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("希尔排序第1轮后=" + Arrays.toString(arr));

        //希尔排序的第二轮排序
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，每组有2个元素），步长是5
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前的元素大于加上步长的那个元素，说明需要交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        System.out.println("希尔排序第2轮后=" + Arrays.toString(arr));

        //希尔排序的第三轮排序
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，每组有2个元素），步长是5
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前的元素大于加上步长的那个元素，说明需要交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第3轮后=" + Arrays.toString(arr));*/

    }

    //对交换式的希尔排序进行优化->移位法
    public static void shellSort2(int[] arr) {

        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素开始，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length;i ++){
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]){
                 while (j - gap >=0 && temp < arr[j - gap]){
                     //移动
                     arr[j] = arr[j - gap];
                     j -= gap;
                 }
                 //当退出while循环后，给temp找到了插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
