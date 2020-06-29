package com.example.kotlindemo.coroutine

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("users/{user}/repos")
    fun getListRepo(@Path("user") user: String): Single<List<Repo>>
}