package com.example.kotlindemo.leakcanary

import android.view.View
import com.example.kotlindemo.mvvm.User
import leakcanary.AppWatcher
import java.lang.ref.PhantomReference
import java.lang.ref.ReferenceQueue
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

class LeakDemo {
    var leakView = ArrayList<View>()//强引用
    var weak = WeakReference<User>(User())//弱引用，可以被GC回收
    var soft = SoftReference<User>(User())//软引用 内存不⾜会被垃圾回收
    var phantom = PhantomReference<User>(User(), ReferenceQueue())//虚引用  不能通过 get() 获得引⽤对象,会被垃圾回收
    fun main() {
        var user = weak.get()
        var softUser = soft.get()
        var phan = phantom.get()//return null
        AppWatcher.objectWatcher.watch(User(), "")
    }
}