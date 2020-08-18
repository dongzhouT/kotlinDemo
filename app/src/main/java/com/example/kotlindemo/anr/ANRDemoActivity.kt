package com.example.kotlindemo.anr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R
import kotlin.concurrent.thread

class ANRDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_n_r_demo)
        Thread.sleep(20)
    }
}
