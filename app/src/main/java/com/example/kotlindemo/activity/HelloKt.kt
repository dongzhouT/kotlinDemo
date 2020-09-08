package com.example.kotlindemo.activity

import kotlin.jvm.internal.Ref

/**
 * 匿名内部类可以修改外部参数
 */
class HelloKt {
    fun show(aa: String?) {
        var i = 0;
        val thread: Thread = object : Thread() {
            override fun run() {
                var k = Ref.IntRef()
                k.element++
                i++
                println(aa)
            }
        }
    }
}