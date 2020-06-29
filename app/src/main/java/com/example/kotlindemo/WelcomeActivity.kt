package com.example.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                var intent = Intent()
                intent.setClass(WelcomeActivity@ this, DemoActivity::class.java)
                startActivity(intent)
            }
            R.id.btn2 -> {
                var intent = Intent()
                intent.setClass(WelcomeActivity@ this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}
