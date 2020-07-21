package com.example.kotlindemo.okhttp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import okhttp3.*
import java.io.IOException
import java.net.InetAddress
import kotlin.concurrent.thread

/**
 *
 */
class OkhttpDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_demo)
        val url = "https://api.github.com/users/dongzhout/repos"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                println("reponse==>:${response.code}")
            }
        })
        thread {
            println("Resolved address:${InetAddress.getAllByName("hencoder.com")[0]}")
        }

    }
}
