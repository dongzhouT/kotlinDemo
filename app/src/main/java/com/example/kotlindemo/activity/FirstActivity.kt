package com.example.kotlindemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import com.bumptech.glide.Glide
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
        var imgUrl = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1440378077,2357629830&fm=26&gp=0.jpg";
        Glide.with(this).load(imgUrl).into(imageview)
        Toast.makeText(this,"hahahaha",Toast.LENGTH_LONG).show()
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