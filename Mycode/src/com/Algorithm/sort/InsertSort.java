package com.Algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0-8000000)数
        }
        //System.out.println("原始数组为：");
        //System.out.println(Arrays.toString(arr));

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    public static void insertSort(int[] arr) {

        //使用for循环来把代码简化
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;//即arr[1]的前面这个数的下标

            //给insertVal 找到一个插入的位置
            //说明：
            //1.insertIndex >= 0保证在给insertVal找插入位置时，不越界
            //2.insertVal < arr[insertIndex] 待插入的数还没有找到适当的位置
            //3.就需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1
            //这里我们判断我们是否需要赋值
            if (insertIndex + 1 == i) {
                arr[insertIndex + 1] = insertVal;
            }
        }

        //System.out.println("排序后的数组为：");
        //System.out.println(Arrays.toString(arr));



        /*
        //逐步推导
        //第一轮{101, 34, 119, 1}；=> {34,101,119,1}
        //第二轮{34,101,119,1} => {34,101,119,1}
        //第三轮{34,101,119,1} => {1,34,101,119}

        //{101, 34, 119, 1}; => {101,101,119,1}
        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1;//即arr[1]的前面这个数的下标

        //给insertVal 找到一个插入的位置
        //说明：
        //1.insertIndex >= 0保证在给insertVal找插入位置时，不越界
        //2.insertVal < arr[insertIndex] 待插入的数还没有找到适当的位置
        //3.就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮插入");
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }

        arr[insertIndex + 1] = insertVal;

        System.out.println("第二轮插入");
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第三轮插入");
        System.out.println(Arrays.toString(arr));

         */
    }
}
