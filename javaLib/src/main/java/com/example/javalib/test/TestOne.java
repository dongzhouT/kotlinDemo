package com.example.javalib.test;

/**
 * 执行顺序
 * TestTwo() static
 * TestOne() static
 * TestTwo
 * TestTwo()
 * TestOne
 * TestOne()
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