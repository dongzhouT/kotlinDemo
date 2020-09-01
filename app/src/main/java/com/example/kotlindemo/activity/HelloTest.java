package com.example.kotlindemo.activity;


import android.widget.Toast;

public class HelloTest {
    public void hello(String hh){
        new Thread(){
            @Override
            public void run() {
                System.out.println(hh);
            }
        }.start();
    }

}
