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
 * idleHandler 在纤细队列空闲的时候做事
 * 用handlerThread创建子线程handler
 */
class HandlerDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_demo)
        var mHandler: Handler
        thread {
            Looper.prepare()
            mHandler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    println("===currentThread=== handle:${msg.what}")
                }
            }

            mHandler.sendEmptyMessage(1)
            mHandler.sendEmptyMessageDelayed(2, 3000)
            Looper.myQueue().addIdleHandler {
                //在消息队列空闲的时候做一些事情，返回值false 只调用一次，true 会调用无限次
                println("===currentThread=== idle:${Thread.currentThread().name}")
                true
            }
            Looper.loop()

            mHandler.post {
                println("===currentThread=== post1:${Thread.currentThread().name}")
                handler_tv.text = Thread.currentThread().name
            }
        }

        val handlerThread = HandlerThread("newThread")
        handlerThread.start()
        var handler = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message) {
                println("===currentThread=== handle:${msg.what},${Thread.currentThread().name}")
            }
        }
        handler.sendEmptyMessage(11)
        handler.sendEmptyMessageDelayed(22, 3000)
        handler.post {
            println("===currentThread=== post2:${Thread.currentThread().name}")
            handler_tv.text = Thread.currentThread().name
        }

    }
}

