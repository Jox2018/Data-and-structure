package com.DataStructures.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建几个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入(方式1)
        //singleLinkedList.add(hero1);
        //singleLinkedList.add(hero2);
        //singleLinkedList.add(hero3);
        //singleLinkedList.add(hero4);

        //加入（方式2）
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的节点");
        //显示
        singleLinkedList.list();

        //测试删除接节点
        System.out.println("删除后的节点");
        singleLinkedList.delete(2);
        singleLinkedList.list();

        //测试求单链表中有效节点的个数
        System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));

        //测试是否得到了倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),3);
        System.out.println("res=" + res);

        //测试栈逆序打印
        System.out.println("测试栈逆序打印,没有改变链表的本身结构~");
        reversePrint(singleLinkedList.getHead());

        //测试单链表反转
        reverseList(singleLinkedList.getHead());
        System.out.println("反转后的链表：");
        singleLinkedList.list();

    }

    /**
     * 合并俩个有序单链表，合并后仍然是有序的
     */

    /**
     * 使用栈将单链表逆序打印
     */
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;//空链表
        }
        //创建一个栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null){
         stack.push(cur);
         cur = cur.next;
        }
        //将栈中的节点进行打印
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    /**
     * 将单链表进行反转
     */
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }

        HeroNode cur = head.next;//先定义一个辅助的指针，帮助我们遍历原来的链表
        HeroNode next = null;//指向当前节点（cur）的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //并从头到尾遍历原来的链表，每遍历一个节点
        //就将其取出，并放在新的链表reverseHead的最前端（不算头节点）
        while (cur != null){
            next = cur.next;//next先指向cur.next节点，先暂时保存当前节点的下一个节点，后面要使用
            cur.next = reverseHead.next;//将cur下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//让指针后移
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }


    /**
     * 获取单链表的倒数第k个节点
     * 思路：
     * 1.编写一个方法，接受head节点，同时接受index
     * 2.index表示是倒数第index个节点
     * 3.先把链表从头到尾遍历得到链表总长度
     * 4.得到size后，从链表的第一个开始遍历（size-index）个，就可以得到
     * 5.找到返回该节点，否则返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //如果链表为空
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到节点的个数
        int size = getLength(head);
        //第二次遍历
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量
        HeroNode cur = head.next;
        for (int i = 0;i < size - index;i++){
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 获取到单链表的节点个数（如果带头节点的链表，需要不统计头节点）
     *
     * @param head 链表的头节点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量,这里没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

/**
 * 定义SingleLinkedList 管理我们的英雄
 */
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动,不存放数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 当不考虑编号的顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新的节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //因为head节点不能动，需要一个辅助变量
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后,将temp后移
            temp = temp.next;
        }
        //当退出这个循环时，temp指向链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //第二种添加方式，根据排名将英雄插入到指定的位置
    //如果有这个排名，则添加失败，并给出提示

    /**
     * 第二种添加方式，根据排名将英雄插入到指定的位置
     * 如果有这个排名，则添加失败，并给出提示
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，仍然通过辅助变量来帮助找到添加的位置
        //因为单链表，我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                //temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                //位置找到了，就在temp的后边插入
                break;
            } else if (temp.next.no == heroNode.no) {
                //说明希望添加的编号已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }

        //判断flag的值
        if (flag) {
            //不能添加，编号存在
            System.out.printf("准备插入的英雄编号 %d 已经存在了！", heroNode.no);
        } else {
            //插入到链表中，temp的后边,temp.next的前边
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息，根据编号来修改信息，no编号不能改
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助节点
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//到达链表最后,已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag == true) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            //没有找到这个节点
            System.out.printf("没有找到编号为%d 的节点,不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     * 思路：
     * 1. head不能动，我们需要找到一个temp辅助节点找到待删除节点的前一个节点
     * 2. 在比较时是比较temp.next.no和需要删除的节点的no做比较
     *
     * @param no
     */
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//表示是否找到待删除的前一个节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除 %d 这个节点不存在\n", no);
        }
    }

    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断链表是否到最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将next后移
            temp = temp.next;
        }
    }
}

/**
 * 每个HeroNode对象就是一个节点
 */
class HeroNode {
    public int no;//编号 data区
    public String name;//data区
    public String nickname;//data区
    public HeroNode next;//指向下一个节点  next区

    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
