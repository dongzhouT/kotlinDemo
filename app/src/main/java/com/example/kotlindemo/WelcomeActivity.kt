package com.example.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kotlindemo.animator.AnimDemoActivity
import com.example.kotlindemo.customLayout.TagLayoutDemoActivity
import com.example.kotlindemo.drawable.DrawableDemoActivity
import com.example.kotlindemo.drawable.HandleActivity
import com.example.kotlindemo.handler.HandlerDemoActivity
import com.example.kotlindemo.multiTouch.MultiTouchDemoActivity
import com.example.kotlindemo.okhttp.OkhttpDemoActivity
import com.example.kotlindemo.recyclerview.RecyclerviewDemoActivity
import com.example.kotlindemo.retrofit.RetrofitDemoActivity
import com.example.kotlindemo.touch.TouchDemoActivity
import com.example.kotlindemo.touch.TwoPagerDemoActivity
import com.example.kotlindemo.view.TextViewDemoActivity
import kotlinx.android.synthetic.main.activity_text_view_demo.*
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        btn1.setOnClickListener(this)
//        btn2.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                var intent = Intent()
                intent.setClass(WelcomeActivity@ this, DemoActivity::class.java)
                startActivity(intent)
            }
//            R.id.btn2 -> {
//                var intent = Intent()
//                intent.setClass(WelcomeActivity@ this, MainActivity::class.java)
//                startActivity(intent)
//            }

        }
    }

    fun onClickRetrofit(view: View) {
        startActivity(Intent(this, RetrofitDemoActivity::class.java))
    }

    fun onClickOkhttp(view: View) {
        startActivity(Intent(this, OkhttpDemoActivity::class.java))
    }

    fun onClickTextDemo(view: View) {
        startActivity(Intent(this, TextViewDemoActivity::class.java))
    }

    fun onClickAnimDemo(view: View) {
        startActivity(Intent(this, AnimDemoActivity::class.java))
    }

    fun onClickDrawableDemo(view: View) {
        startActivity(Intent(this, DrawableDemoActivity::class.java))
    }

    fun onClickTagDemo(view: View) {
        startActivity(Intent(this, TagLayoutDemoActivity::class.java))
    }

    fun onClickRv(view: View) {
        startActivity(Intent(this, RecyclerviewDemoActivity::class.java))
    }

    fun onClickHandler(view: View) {
        startActivity(Intent(this, HandlerDemoActivity::class.java))
    }

    fun onClickTouch(view: View) {
        startActivity(Intent(this, TouchDemoActivity::class.java))
    }

    fun onClickTwoPager(view: View) {
        startActivity(Intent(this, TwoPagerDemoActivity::class.java))
    }

    fun onClickMultiTouch(view: View) {
        startActivity(Intent(this, MultiTouchDemoActivity::class.java))
    }
}
