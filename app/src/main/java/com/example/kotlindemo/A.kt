package com.example.kotlindemo

import android.util.Log
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
//    val arrayOf: Array<Int> = arrayOf(1, 2, 3)
//    intArrayOf(1, 2, 3)
//    repeat(100) {
//        println(it)
//    }
//    repeat(100) { index ->
//        println(index)
//    }
    log("log")
}

inline fun log(text: String) {
    Log.e("tag", text)
    aa().apply {

    }
    aa().let {

    }
    val also = aa().also {

    }
    var i = aa().run<aa, Int> {
        return@run 3
    }
}

class aa {
    var token: String by Saver()

    class Saver {
        operator fun getValue(aa: aa, property: KProperty<*>): String {
            return ""

        }

        operator fun setValue(aa: aa, property: KProperty<*>, s: String) {

        }

    }
}
