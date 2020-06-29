package com.example.kotlindemo.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_corountine_demo.*
import retrofit2.Retrofit
import kotlin.concurrent.thread

class CorountineDemoActivity : AppCompatActivity() {
    val retrofit=Retrofit.Builder().baseUrl("https://api.github.com/")
//            .addConverterFactory()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corountine_demo)
        thread {
            Thread.sleep(3000)
            runOnUiThread {

                id_textview.text="hehehe"
            }
        }

        id_textview.text="hahaha"
    }
}