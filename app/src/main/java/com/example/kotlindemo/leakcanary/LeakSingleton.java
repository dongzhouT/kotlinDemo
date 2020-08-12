package com.example.kotlindemo.leakcanary;

import android.content.Context;

public class LeakSingleton {
    private Context context;
    private static LeakSingleton mInstance;

    private LeakSingleton(Context context) {
        context = context;
    }

    public static LeakSingleton getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LeakSingleton.class) {
                if (mInstance == null) {
                    return new LeakSingleton(context);
                }
            }
        }
        return mInstance;
    }
}
