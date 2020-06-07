package com.DataStructures.stack;

public class Calculator {
    public static void main(String[] args) {
        //根据思路，完成表达式的运算
        String expression = "30+2*6-2";//这里处理多位数不成功，ASCII问题
        //创建俩个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);//数字栈
        ArrayStack2 operStack = new ArrayStack2(10);//符号栈
        //定义我们需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的结果保存到ch中
        String keepNum = "";//用于拼接多位数
        //开始循环扫描expression
        while (true) {
            //依次得到expression每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，做相应的处理
            if (operStack.isOperator(ch)) {
                //看当前栈是否为空
                if (!operStack.isEmpty()) {
                    //不为空
                    //判断优先级,如果当前操作符的优先级小于等于栈中的操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //把要传入的符号入符号栈
                        operStack.push(ch);
                    } else {
                        //优先级大于栈中的操作符，直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //为空,直接入栈
                    operStack.push(ch);
                }
            } else {
                //numStack.push(ch);扫描的1是字符1，ASCII表
                //numStack.push(ch - 48);
                //分析思路：
                //1.当处理多位数时，不能发现是一个数就立即入数栈，他可能是多位数
                //2.在处理数时，需要向expression的表达式的index 再看一位，如果是数就继续扫描，如果是符号才入栈
                //3.我们需要定义一个变量，字符串变量，用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression已经是最后一位，直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，则继续扫描
                    if (operStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的！！！要把keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index + 1 并判断是否扫描到最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕
        while (true) {
            //如果符号栈为空，计算结束，数栈中只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s 结果为：%d \n", expression, numStack.peek());
    }
}


/**
 * 定义一个ArrayStack 表示栈，需要扩展功能
 */
class ArrayStack2 {
    private int maxSize;// 栈的大小
    private int[] stack;// 数组，数组模拟栈，数据就放在该数组中
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈,将栈顶的数据弹出
    public int pop() {
        //判断是否栈空
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈，从栈顶往下遍历]
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }

    //显示栈顶的元素，但是不是真正的弹出
    public int peek() {
        return stack[top];
    }

    //返回运算符的优先级，优先级是我们来确定的，优先级使用数字表示
    //数字越大，优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;//假定目前表达式只有+-*/
        }
    }

    //判断是不是一个运算符
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
}
