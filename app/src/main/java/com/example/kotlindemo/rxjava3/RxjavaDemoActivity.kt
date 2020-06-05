package com.example.kotlindemo.rxjava3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle

import com.example.kotlindemo.R;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiConsumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.internal.operators.single.SingleDelay
import io.reactivex.rxjava3.internal.operators.single.SingleMap
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit;
import java.util.concurrent.TimeUnit

class RxjavaDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_rxjava_demo)
        func()
    }

    fun func() {
        var single = Single.just(11)
        var singleString = SingleMap<Int, String>(single, object : Function<Int?, String?> {
            override fun apply(t: Int?): String? {
                return t.toString()
            }
        })
        singleString.delay(1, TimeUnit.SECONDS)
        singleString.subscribe(object : SingleObserver<String?> {
            override fun onSuccess(t: String?) {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onError(e: Throwable?) {
            }
        })
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long?> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onNext(t: Long?) {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })


    }

}
