package com.example.kotlindemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.blankj.utilcode.util.SizeUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    CameraView cameraView;
    float rotateAngle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.layout_multipletextview);
        cameraView = findViewById(R.id.cameraView);
        ObjectAnimator anim = ObjectAnimator.ofFloat(cameraView, "CAMERA_ANGLE", 360f);
        anim.setStartDelay(1000);
        anim.setDuration(10000);
        ValueAnimator animator=ValueAnimator.ofFloat(0,100f);
        anim.start();

    }
}
