package com.example.javalib.threadinteraction;

import com.example.javalib.thread.TestDemo;

public class ThreadInteractionDemo implements TestDemo {
    @Override
    public void runTest() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    Thread.interrupted();
                    if (isInterrupted()) {
                        return;
                    }
                    System.out.println("number=" + i);
                }
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread1.stop();//直接终止线程,结果不可预期
        thread1.interrupt();//java.lang.InterruptedException: sleep interrupted
    }
}
