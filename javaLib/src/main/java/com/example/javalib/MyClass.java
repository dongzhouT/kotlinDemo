package com.example.javalib;

public class MyClass {
    public static void main(String[] args) {
//        System.out.println("hello");
//        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
//        new Thread() {
//            @Override
//            public void run() {
//                threadLocal.set(1);
//                threadLocal.get();
//            }
//        };
//        int a = 0b1111;
//        int b = (a & 0b10) ;
//        System.out.println("b=" + Integer.toHexString(b));
        String sss="57333ef6-084f-472b-a4e2-809ccbac8212.492986bf-2a67-485f-b04f-e9bd8f6b75cc";
        System.out.println(sss.contains("."));
        System.out.println(sss.split("\\.").length);
    }
}
