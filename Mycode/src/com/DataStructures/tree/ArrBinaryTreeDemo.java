package com.DataStructures.tree;

/**
 * 顺序存储二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrayBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        arrBinaryTree.preOrder();
        System.out.println("中序遍历");
        arrBinaryTree.infixOrder();
        System.out.println("后序遍历");
        arrBinaryTree.postOrder();//4,5,2,6,7,3,1
    }
}

//编写一个ArrayBinaryTree,实现顺序存储二叉树遍历
class ArrBinaryTree {

    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder方法
    public void preOrder(){
        this.preOrder(0);
    }

    public void infixOrder(){
        this.infixOrder(0);
    }

    public void postOrder(){
        this.postOrder(0);
    }

    /**
     * 编写一个方法完成顺序存储二叉树的一个前序遍历
     *
     * @param index 表示数组的下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        //防止数组越界
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }

        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的中序遍历");
        }

        if ((index * 2 + 1) < arr.length){
            infixOrder(index * 2 + 1);
        }

        System.out.println(arr[index]);

        if ((index * 2 + 2) < arr.length){
            infixOrder(index * 2 + 2);
        }
    }

    public void postOrder(int index){
        if (arr == null || arr.length == 0 ){
            System.out.println("数组为空，不能按照二叉树的后序遍历");
        }

        if ((index * 2 + 1) < arr.length){
            postOrder(index * 2 + 1);
        }

        if ((index * 2 + 2) < arr.length){
            postOrder(index * 2 + 2);
        }

        System.out.println(arr[index]);
    }
}