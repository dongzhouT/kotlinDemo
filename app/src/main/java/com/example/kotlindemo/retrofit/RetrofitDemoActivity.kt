package com.example.kotlindemo.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_retrofit_demo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * OkHttpCall(RequestFactory requestFactory,Object[] args,okhttp3.Call.Factory callFactory,Converter<ResponseBody, T> responseConverter)
 *
 *   HttpServiceMethod.java
 *   @Override
 *   final @Nullable ReturnT invoke(Object[] args) {
 *      Call<ResponseT> call = new OkHttpCall<>(requestFactory, args, callFactory, responseConverter);
 *      return adapt(call, args);
 *   }
 * okhttp3.Call call = callFactory.newCall(requestFactory.create(args));
 * callFactory = new OkHttpClient();在 Retrofit build()中初始化
 * requestFactory和args创建一个okhttp的request，然后调用okhttp3.enqueue发送网络请求
 * responseConverter->addConverterFactory(GsonConverterFactory.create()) 用于请求结果的json格式解析
 * addCallAdapterFactory 用来切线程，自带的call是切换到主线程
 *
 */
class RetrofitDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_demo)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val service = retrofit.create(GithubService::class.java)
        val listRepos = service.listRepos("dongzhout")
        listRepos.enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                print("Reponse tt=${t.message}")
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                println("Reponse:${response.body()!![0].name}")
                tv_text.text = response.body()!![0].name
            }

        })
    }
}
