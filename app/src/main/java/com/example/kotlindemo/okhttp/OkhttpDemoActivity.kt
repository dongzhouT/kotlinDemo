package com.example.kotlindemo.okhttp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.facebook.stetho.okhttp3.StethoInterceptor
import kotlinx.android.synthetic.main.activity_okhttp_demo.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger.*
import java.io.IOException
import java.net.InetAddress
import java.util.logging.Logger
import kotlin.concurrent.thread

/**
 * OkHttp
 * RealCall.getResponseWithInterceptorChain() 链式调用 拦截器
 * proceed（）intercept
 * RetryAndFollowUpInterceptor 重试和跟踪 请求失败的重试和重定向
 * BridgeInterceptor 桥接 content-type和gzip等预处理
 * CacheInterceptor 缓存命中
 * ConnectInterceptor 建立TCP或TLS连接，并创建对应的HttpCodec对象（HTTP1还是HTTP2）。
 * findConnection 最多有五次建立连接，
 *  1.直接使用已经建立的可用的(符合条件的)连接
 *  2.从连接池中找可用连接，不使用连接合并(routes) connection coalescing，不使用多路复用， Attempt to get a connection from the pool
 *  3.从连接池中找可用连接，使用连接合并(routes)，使用多路复用 This could match due to connection coalescing.
 *  4.建立连接 Do TCP + TLS handshakes
 *  5.从连接池中找可用连接，[只拿]多路复用，加了同步锁，防止创建多个相同连接，造成资源浪费
 * CallServerInterceptor 去请求与相应的I/O操作，发请求读响应
 *
 */
class OkhttpDemoActivity : AppCompatActivity() {
    private var ii = 0
    public var aa = 0
    internal var bb = 0

    var handler:Handler=Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_demo)
        val url = "https://api.github.com/users/octocat/repos"
        val client = OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().apply {
//                    setLevel(HttpLoggingInterceptor.Level.BODY)
//                })
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                println("reponse==>:${response.code}")
                runOnUiThread {
                    id_tv.text = response.code.toString()
                }

            }
        })
        thread {
//            TODO()
            println("Resolved address:${InetAddress.getAllByName("hencoder.com")[0]}")
        }

    }
}
