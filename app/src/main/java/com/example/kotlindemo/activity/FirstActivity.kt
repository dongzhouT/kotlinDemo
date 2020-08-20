package com.example.kotlindemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewTreeObserver
import com.example.kotlindemo.R
import com.example.kotlindemo.log
import kotlinx.android.synthetic.main.activity_drawable_demo.*
import kotlinx.android.synthetic.main.activity_first.*

/**
 * Activity切换生命周期回调：
 * A->B
 * A:onPause
 * B:onCreate
 * B:onStart
 * B:onResume
 * A:onStop
 *
 * B back to A
 * B:onPause
 * A:onRestart
 * A:onStart
 * A:onResume
 * B:onStop
 * B:onDestroy
 *
 */
class FirstActivity : AppCompatActivity() {
    private val tag = "LifeCycle A:";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        showLog("onCreate")
        println("111 width=${btn.width},height=${btn.height}")
        btn.viewTreeObserver.addOnGlobalLayoutListener(onLayoutListener)
    }

    val onLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            println("222 width=${btn.width},height=${btn.height}")
            btn.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }



    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        showLog("onSaveInstanceState")
    }

    override fun onRestart() {
        super.onRestart()
        showLog("onRestart")
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

    fun startB(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

}