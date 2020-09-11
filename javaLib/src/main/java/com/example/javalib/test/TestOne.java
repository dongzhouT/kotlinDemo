package com.example.javalib.test;

/**
 * 执行顺序
 * TestTwo() static
 * TestOne() static
 * TestTwo
 * TestTwo()
 * TestOne
 * TestOne()
 * 父类静态代码块——>子类静态代码块——>父类代码块——>父类构造方法——>子类代码块——>子类构造方法
 */
public class TestOne extends TestTwo {
    public TestOne() {
        System.out.println("TestOne()");
    }

    {
        System.out.println("TestOne");
    }

    static {
        System.out.println("TestOne() static");
    }

    public static void main(String[] args) {
        new TestOne();
        new TestOne();
    }

}

class TestTwo {
    public TestTwo() {
        System.out.println("TestTwo()");
    }

    {
        System.out.println("TestTwo");
    }

    static {
        System.out.println("TestTwo() static");
    }

    public static void find() {
        System.out.println("find");
    }
}