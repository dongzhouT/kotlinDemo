package com.example.kotlindemo;

import android.app.Activity;
import android.app.IntentService;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public static void main(String[] args) {
        System.out.println("hello");
        Looper.myLooper();
        Looper.loop();
        Looper.getMainLooper();
        Looper.prepareMainLooper();
        Handler handler=new Handler(Looper.getMainLooper());
        Message message = handler.obtainMessage();
        HandlerThread handlerThread=new HandlerThread("second");
        handlerThread.start();
        Handler handlerSecond=new Handler(handlerThread.getLooper());
        handlerSecond.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        AsyncTask task=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }
        };
        task.cancel(true);

    }
}
