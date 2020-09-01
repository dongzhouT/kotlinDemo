package com.example.kotlindemo

class HelloKt {
    fun hello(data:String){
        var thread=Thread(){
            fun run(){
//                data="abc"
                println("data=${data}")
                print("hahaha")
            }
        }
        thread.start()

    }
}
fun main(){
    print("start")
    HelloKt().hello("haha")
}