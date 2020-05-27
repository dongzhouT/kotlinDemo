package com.example.javalib.thread;

public class WaitDemo implements TestDemo {
    private String sharedString;
    private Object monitor = new Object();

    private synchronized void initString() {
        sharedString = "rengwuxian";
        notifyAll();
    }

    private synchronized void printString() {
        while (sharedString == null) {
            try {
                wait();
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
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initString();
            }
        };

        Thread thread2 = new Thread() {
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
        thread1.start();
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("haha");

    }
}
