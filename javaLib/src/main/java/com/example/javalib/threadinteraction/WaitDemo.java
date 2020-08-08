package com.example.javalib.threadinteraction;

import com.example.javalib.thread.TestDemo;

public class WaitDemo implements TestDemo {
    private volatile String sharedString;
    private Object monitor = new Object();

    private synchronized void initString() {
        sharedString = "rengwuxian";
        notifyAll();
    }

    private synchronized void printString() {
        System.out.println("printString start");
        while (sharedString == null) {
            try {
                System.out.println("waiting:"+sharedString);
                wait();
                System.out.println("waiting end:"+sharedString);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("name=" + sharedString);
    }

    private void initString2() {
        synchronized (monitor) {
            sharedString = "rengwuxian";
            monitor.notifyAll();
        }

    }

    private void printString2() {
        synchronized (monitor) {
            while (sharedString == null) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("name=" + sharedString);
        }

    }

    @Override
    public void runTest() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printString();

            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                yield();//让路
                initString();
            }
        };
        thread1.start();
        thread2.start();
//        try {
//            thread1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("haha");

    }
}
