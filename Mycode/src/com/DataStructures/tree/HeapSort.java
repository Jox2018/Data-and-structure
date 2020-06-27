package com.DataStructures.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组升序排列
        //int[] arr = {4, 6, 8, 5, 9};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0-8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);
        heapSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    //编写一个堆排序的方法
    public static void heapSort(int arr[]) {
        int temp = 0;
       // System.out.println("堆排序！！");

        /*
        adjustHeap(arr,1,arr.length);
        System.out.println("第一次" + Arrays.toString(arr)); //4, 9, 8, 5, 6

        adjustHeap(arr,0,arr.length);
        System.out.println("第二次" + Arrays.toString(arr)); //9, 6, 8, 5, 4
        */

        //完成最终代码
        //将无序序列构建成一个堆，第一个非子节点是arr.length - 1,第二个是arr.length - 1 - 1
        for (int i = arr.length / 2 - 1; i >= 0; i --){
            adjustHeap(arr,i,arr.length);
        }
        //上面这个for循环完成之后已经将数组调整为一个大顶堆
        //第一次将整个数组排成大顶堆的时候就是从后面开始排，往前走，

        /**
         * 将堆顶元素与末尾元素交换，将最大元素沉到数组末尾
         * 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行
         */
        for (int j = arr.length - 1; j > 0;j --){  //这一次完了以后已经排完一个了，所以arr.length - 1
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        //System.out.println(Arrays.toString(arr));
    }

    //将一个数组（二叉树），调整成一个大顶堆

    /**
     * 功能：完成将以i对应的非叶子节点的数，调整成大顶堆
     * 举例 int[] arr = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => {4, 9, 8, 5, 6}
     * 如果再次调用adjustHeap 传入的是i = 0 => 得到{4, 9, 8, 5, 6} => {9, 6, 8, 5, 4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点的在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i];//先取出当前元素的值保存在一个临时变量
        //开始调整
        //说明
        //1. k = i * 2 + 1 k是i节点的左子节点  k * 2 + 1是k的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { //说明左子节点的值小于右子节点的值,k + 1是右子节点的值
                k++; //k指向右子节点
            }
            if (arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;// !!! i指向k,继续循环比较
            }else {
                break;//!调整的时候是从下到上走的
            }
        }

        //当for循环结束后，已经将以i为父节点的树的最大值，放在了最顶上（局部）
        arr[i] = temp;//将temp放到调整后的位置
    }
}
