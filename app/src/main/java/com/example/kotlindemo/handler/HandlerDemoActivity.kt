package com.example.kotlindemo.handler

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_handler_demo.*
import kotlinx.android.synthetic.main.activity_okhttp_demo.*
import kotlin.concurrent.thread

/**
 * Handler
 * sendMessageAtTime
 */
class HandlerDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_demo)
        thread {
            Looper.prepare()
            var handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    println("===currentThread=== handle:${msg.what}")
                }
            }
            handler.post {
                println("===currentThread=== post:${Thread.currentThread().name}")
                handler_tv.text = Thread.currentThread().name
            }
            handler.sendEmptyMessage(1)
            handler.sendEmptyMessageDelayed(2, 3000)
            Looper.myQueue().addIdleHandler {
                //在消息队列空闲的时候做一些事情，返回值false 只调用一次，true 会调用无限次
                println("===currentThread=== idle:${Thread.currentThread().name}")
                true
            }
            Looper.loop()
        }

    }
}

