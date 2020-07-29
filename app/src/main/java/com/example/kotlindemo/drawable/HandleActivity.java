package com.example.kotlindemo.drawable;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kotlindemo.R;

public class HandleActivity extends Activity {
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            System.out.println("###what=###"+msg.what);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_demo);
        mHandler.sendEmptyMessage(1);
        mHandler.sendEmptyMessageDelayed(2,2000);
        mHandler.sendEmptyMessage(3);
    }
}