package com.example.kotlindemo.coroutine

import android.provider.Settings
import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import kotlin.concurrent.thread

class CoroutineDemo {
    private var scope = MainScope()
    fun code1() {
        Thread.sleep(1000)

        scope.launch {
            coroutineScope {
                launch {

                }
                launch {

                }
            }

        }
        //取消协程
        scope.cancel()

        GlobalScope.launch(Dispatchers.Main) {

        }

    }

    suspend fun ioCode1() {
        withContext(Dispatchers.IO) {
            var same = true
            print("$same same")

        }

    }
}