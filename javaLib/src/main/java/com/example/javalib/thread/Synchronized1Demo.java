package com.example.javalib.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile
 */
public class Synchronized1Demo implements TestDemo {
    private volatile boolean running = true;

    private void stop() {
        running = false;
    }

    @Override
    public void runTest() {
        new Thread() {
            @Override
            public void run() {
                while (running) {
                }
            }
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop();
    }

    public static void main(String[] args) {
        AtomicInteger count=new AtomicInteger(0);
        count.incrementAndGet();

    }
    ReentrantLock lock=new ReentrantLock();
    private int x=0;
    private void count(){
        lock.lock();
        try {
            x++;
        }finally {
            lock.unlock();
        }
    }
}
