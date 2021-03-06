package com.DataStructures.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        //测试
        System.out.println("双向链表的测试");

        //创建几个节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();

        //修改测试
        System.out.println("修改后的链表");
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();

        System.out.println("删除后的链表");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
    }


    /**
     * 创建一个双向链表的类
     */
    static class DoubleLinkedList {
        //先初始化一个头节点，头节点不要动,不存放数据
        private HeroNode2 head = new HeroNode2(0, "", "");

        public HeroNode2 getHead() {
            return head;
        }

        /**
         * //遍历双向链表的方法，显示方法
         */
        public void list() {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //因为头节点不能动，需要一个辅助变量来遍历
            HeroNode2 temp = head.next;
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

        /**
         * 按照编号添加双向链表
         */

        /**
         * 添加一个节点到双向链表的最后
         *
         * @param heroNode
         */
        public void add(HeroNode2 heroNode) {
            //因为head节点不能动，需要一个辅助变量
            HeroNode2 temp = head;
            //遍历链表，找到最后
            while (true) {
                if (temp.next == null) {
                    break;
                }
                //如果没有找到最后,将temp后移
                temp = temp.next;
            }
            //当退出这个循环时，temp指向链表的最后
            //形成一个双向链表
            temp.next = heroNode;
            heroNode.pre = temp;
        }

        /**
         * 修改一个节点的内容，可以看到双向链表节点内容修改和单向链表几乎一样
         */
        public void update(HeroNode2 newHeroNode) {
            //判断链表是否为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //找到需要修改的节点，根据no编号
            //定义一个辅助节点
            HeroNode2 temp = head.next;
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
         * 从双向链表中删除节点
         * 说明
         * 1，对于双向链表，可以直接找到要删除的这个节点
         * 2. 找到后，自我删除即可
         */
        public void delete(int no) {
            //判断当前链表是否为空
            if (head.next == null) {
                System.out.println("链表为空，无法删除");
            }
            HeroNode2 temp = head.next;//辅助指针
            boolean flag = false;//表示是否找到待删除的前一个节点
            while (true) {

                //已经找到链表的最后节点
                if (temp == null) {
                    break;
                }
                if (temp.no == no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            //判断flag
            if (flag) {
                temp.pre.next = temp.next;
                //这里代码有问题？如果删除的是最后一个节点，会空指针异常,text.next已经是null
                //如果是最后一个节点，不需要执行下面这个语句
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
            } else {
                System.out.printf("要删除 %d 这个节点不存在\n", no);
            }
        }
    }

    static class HeroNode2 {
        public int no;//编号 data区
        public String name;//data区
        public String nickname;//data区
        public HeroNode2 next;//指向下一个节点  next区  默认为null
        public HeroNode2 pre;//指向前一个节点 pre区  默认为null

        public HeroNode2(int hNo, String hName, String hNickname) {
            this.no = hNo;
            this.name = hName;
            this.nickname = hNickname;
        }

        @Override
        public String toString() {
            return "HeroNode2{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }
}

