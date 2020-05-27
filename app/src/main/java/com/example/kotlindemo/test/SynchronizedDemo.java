package com.example.kotlindemo.test;

public class SynchronizedDemo {
    int x = 0;

    private void count() {
        x++;
    }

    public void runTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    count();
                }
                System.out.println("thread1 x=" + x);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    count();
                }
                System.out.println("thread2 x=" + x);
            }

        }).start();

    }



    //psvm
    public static void main(String[] args) {
        System.out.println("hello");
//        SynchronizedDemo demo = new SynchronizedDemo();
//        demo.runTest();
    }
}
