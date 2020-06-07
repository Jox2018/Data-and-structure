package com.DataStructures.stack;

import javax.security.auth.Subject;
import java.util.*;

public class PolandNotation {
    public static void main(String[] args) {

        //将一个中缀表达式转为后缀表达式的功能
        //说明
        //1. 1+((2+3)*4)-5 => 转成 1 2 3 + 4 * + 5 -
        //2. 因为直接对一个字符串操作，不方便，因此先将字符串"1+((2+3)*4)-5"转为中缀表达式对应的list
        //      即"1+((2+3)*4)-5" => ArrayList[1,+,(,(,2,+.......]
        //3. 将得到的中缀表达式对应的list => 后缀表达式对应的list
        //      即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(list);
        System.out.println(parseSuffixExpressionList);







        /*
        //先定义一个逆波兰表达式
        // （3+4）*5 - 6 => 3 4 + 5 * 6 -
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        //String suffixExpression = "3 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1.先将3 4 + 5 * 6 - 放入到ArrayList中
        //2.将ArrayList传给一个方法，配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        System.out.println("输出结果为：");
        int calculate = calculate(rpnList);
        System.out.println(calculate);

         */
    }

    /**
     * 将一个逆波兰表达式依次将数据和运算符放入到ArrayList中
     */
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele :
                split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算
     * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，
     * 用运算符对它们做相应的计算（次顶元素 和 栈顶元素），并将结果入栈；重复上述过程直到表达式最右端，
     * 最后运算得出的值即为表达式的结果
     */
    public static int calculate(List<String> ls) {
        //只需要一个栈即可
        Deque<String> stack = new ArrayDeque<String>();
        //遍历ls
        for (String item :
                ls) {
            //这个使用正则表达式来取出数
            if (item.matches("\\d+")) {
                //匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算异常");
                }
                //把res入栈
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将中缀表达式转为对应的list
     */
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<String>();
        int i = 0;//指针，索引，用于遍历s
        String str;//对多位数的拼接
        char c;//每遍历到一个字符就放到c
        while (i < s.length()) {
            //如果c是一个非数字,就需要加入到ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                //如果是一个数，需要考虑多位数的问题
                str = "";//先将str置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;//拼接
                    i ++;
                }
                ls.add(str);
            }
        }
        return ls;
    }

    /**
     * 将得到的中缀表达式对应的list => 后缀表达式对应的list
     * 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
     */
    public static List<String> parseSuffixExpressionList(List<String> list){
        //定义俩个栈
        Deque<String> stack1 = new ArrayDeque<String>();//运算符s1栈
        //说明：因为s2这个栈整个操作过程中没有pop操作，这里不用栈，直接使用一个List替代
        //Deque<String> stack2 = new ArrayDeque<String>();//存放中间结果s2栈
        ArrayList<String> s2 = new ArrayList<String>();

        //遍历ls
        for (String item :
                list) {
            //如果是一个数，加入到s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")  || stack1.peek() == null){
                stack1.push(item);
            }else if (item.equals(")")){
                while (!stack1.peek().equals("(")){
                    s2.add(stack1.pop());
                }
                stack1.pop();//将（ 弹出
            }else {
                //当item的优先级小于或者等于栈顶的运算符的优先级，将s1栈顶的运算符弹出并加入到s2中
                //问题：缺少一个比较优先级高低的方法
                while (stack1.size() != 0 && Operation.getValue(stack1.peek()) >= Operation.getValue(item)){
                    s2.add(stack1.pop());
                }
                //还需要将item压入栈中
                stack1.push(item);
            }
        }
        while (stack1.size() != 0){
            s2.add(stack1.pop());
        }
        return s2;//因为存放到数组中，不需要再进行逆序输出
    }

}

/**
 * 编写一个类Operation 可以返回一个运算符 对应的优先级
 */
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    /**
     * 返回对应的优先级数字
     */
    public static int getValue(String operation){
        int result =0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }

        return result;
    }

}


