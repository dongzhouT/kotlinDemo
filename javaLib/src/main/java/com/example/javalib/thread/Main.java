package com.example.javalib.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
//        new WaitDemo().runTest();
//        new ThreadInteractionDemo().runTest();
//        callable();
        new Synchronized1Demo().runTest();
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
}
