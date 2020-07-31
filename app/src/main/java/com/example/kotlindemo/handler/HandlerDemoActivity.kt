package com.example.kotlindemo.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
            var handler = Handler()
            handler.post {
                handler_tv.text = Thread.currentThread().name
            }
            handler.sendEmptyMessage(1)
            handler.sendEmptyMessageDelayed(2, 3000)
            Looper.loop()
            handler.removeCallbacksAndMessages(null)
        }

    }
}
