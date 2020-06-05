package com.example.javalib;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("hello");
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        new Thread() {
            @Override
            public void run() {
                threadLocal.set(1);
                threadLocal.get();
            }
        };
        int a = 0b1111;
        int b = (a & 0b10) ;
        System.out.println("b=" + Integer.toHexString(b));
    }
}
