package com.example.kotlindemo.drawable

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.kotlindemo.R
import com.example.kotlindemo.log

class DrawableDemoActivity : Activity() {
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            println("===what===>${msg.what}")
        }

    }
    private val mHandler2 = Handler {
        println("###what=${it.what}")
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_demo)
        //bitmap和drawable互转操作
//        var bitmap=Bitmap.createBitmap(50.dp.toInt(), 50.dp.toInt(),Bitmap.Config.ARGB_8888)
//        bitmap.toDrawable(resources)
//        var drawable=ColorDrawable()
//        drawable.toBitmap(50,80,Bitmap.Config.ARGB_8888)
        println("###what onCreate1")
        mHandler.sendEmptyMessage(1)
        mHandler.sendEmptyMessageDelayed(2, 2000)
        mHandler.sendEmptyMessage(3)
        mHandler2.sendEmptyMessage(1)
        mHandler2.sendEmptyMessageDelayed(2, 2000)
        mHandler2.sendEmptyMessage(3)
    }
}
fun main(){
    println("1111222")
}
