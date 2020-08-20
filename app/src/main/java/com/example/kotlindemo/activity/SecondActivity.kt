package com.example.kotlindemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.kotlindemo.R
import com.example.kotlindemo.log

class SecondActivity : AppCompatActivity() {
    private val tag = "LifeCycle B:";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        showLog("onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        showLog("onSaveInstanceState")
    }

    override fun onStart() {
        super.onStart()
        showLog("onStart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        showLog("onNewIntent")
    }

    override fun onResume() {
        super.onResume()
        showLog("onResume")
    }

    override fun onPause() {
        super.onPause()
        showLog("onPause")
    }

    override fun onStop() {
        super.onStop()
        showLog("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("onDestroy")
    }

    fun showLog(msg: String) {
        log(tag + msg)
    }
}