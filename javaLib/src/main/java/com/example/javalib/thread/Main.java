package com.example.javalib.thread;

import com.example.javalib.threadinteraction.ThreadInteractionDemo;
import com.example.javalib.threadinteraction.WaitDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
//        new WaitDemo().runTest();
        fun();
//        new ThreadInteractionDemo().runTest();
//        callable();
//        new Synchronized1Demo().runTest();
    }

    private static void callable() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Done!";
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(callable);
        System.out.println("start");
        try {
            String result = future.get();
            System.out.println("result=" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void fun() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                10,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20)
                , new ThreadPoolExecutor.CallerRunsPolicy());
        new LinkedBlockingQueue();
        executor.allowCoreThreadTimeOut(true);
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 15; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread:" + Thread.currentThread().getName() + ":count=" + count.getAndIncrement());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
//            Future future=executor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("thread:" + Thread.currentThread().getName() + ":count=" + count.getAndIncrement());
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        }
    }
}
