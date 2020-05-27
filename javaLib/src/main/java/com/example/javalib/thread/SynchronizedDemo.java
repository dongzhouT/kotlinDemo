package com.example.javalib.thread;

public class SynchronizedDemo {
    private volatile int x = 0;

    private synchronized void count() {
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

    public static void main(String[] args) {
//        SynchronizedDemo demo = new SynchronizedDemo();
//        demo.runTest();
//        new Thread().interrupt();
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_00_000; i++) {
                    if (isInterrupted()) {
                        return;
                    }
                    System.out.println("number=" + i);
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
        thread.interrupt();
        Thread.interrupted();
    }
}
