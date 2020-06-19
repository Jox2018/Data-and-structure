package com.DataStructures.tree;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //以ArrayList为例，看底层是如何进行数组扩容的
        //ArrayList底层维护了transient Object[] elementData;


        /**
         * ArrayList底层仍然是进行了数组扩容
         */
        ArrayList arrayList = new ArrayList();
    }
}
