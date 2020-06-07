package com.DataStructures.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {

        System.out.println("测试数组模拟环形队列");
        //最大实际空间是4，但是可用空间为3，要留一个空间出来作为约定
        CircleArray circleArray = new CircleArray(4);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列中取数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接受一个字符串
            switch (key) {
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.printf("取得的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头的数据
                    try {
                        int res = circleArray.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");

    }

    static class CircleArray {
        private int maxSize;//数组最大容量
        private int front;//指向队列的第一个元素，初始值为0
        private int rear;//指向队列最后一个元素的后一个位置，希望空出一个位置作为约定，初始值为0
        private int[] arr;//该数组用于存放数据，模拟队列

        public CircleArray(int arrMaxSize) {
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = 0;
            rear = 0;
        }

        //判断队列是否满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        //判断队列是否为空
        public boolean isEmpty() {
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int n) {
            //判断队列是否满
            if (isFull()) {
                System.out.println("队列满，不能加入数据");
                return;
            }
            //直接将数据加入
            arr[rear] = n;
            //将rear后移，必须取模
            rear = (rear + 1) % maxSize;
        }

        //数据出队列
        public int getQueue() {
            //判断队列是否空
            if (isEmpty()) {
                //通过抛出异常
                throw new RuntimeException("队列空，不能取数据");
            }
            //这里需要分析出front是指向队列的第一个元素
            //1. 先把front对应的值保存到一个临时的变量
            //2. 将front后移
            //3. 将临时保存的变量返回
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        //显示队列的所有数据
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列空的，没有数据");
            } else {
                //思路：从front开始遍历，遍历多少个元素
                //环形队列，模一个maxSize看是否越界
                for (int i = front; i < front + size(); i++) {
                    System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
                }
            }
        }

        //求出当前队列有效数据的个数
        public int size() {
            //rear = 1
            //front = 0
            //maxSize = 3
            return (rear + maxSize - front) % maxSize;
        }

        //显示队列的头数据，不是取出数据
        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列空的，没有数据");
            }
            return arr[front];
        }
    }
}
