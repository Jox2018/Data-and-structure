package com.DataStructures.linkedlist;

public class Josephus {
    public static void main(String[] args) {
        //构建环形链表和遍历是否正确
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList {
    //创建一个first节点,当前没有编号
    Boy first = new Boy(-1);

    /**
     * 添加小孩节点，构建成一个环形链表
     *
     * @param nums
     */
    public void addBoy(int nums) {
        //验证数据校验
        if (nums < 1) {
            System.out.println("数据不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成一个环状，这个环只有一个小孩
                curBoy = first;//让curBoy指向第一个小孩，first不能动，通过辅助变量来实现
            } else {
                curBoy.setNext(boy);//连起当前节点到新节点的线
                boy.setNext(first);//boy是新节点，连起新节点到开头节点的线
                curBoy = boy;//辅助指针向前移动
            }
        }
    }

    /**
     * 遍历当前环形链表
     */
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此仍然需要一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    /**
     * 根据用户的输入计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                //说明helper指向了最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动 k - 1 次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                //说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时移动countNum-1次，然后出圈
            for (int j = 0; j < countNum - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的就是要出圈的节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这是将first指向的小孩节点出圈
            first = first.getNext();//让first指针移动
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n",first.getNo());
    }


}

/**
 * 先创建一个Boy类，表示一个节点
 */
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
