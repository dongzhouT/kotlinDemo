package com.example.kotlindemo.generic;

import java.io.Closeable;

//多个泛型参数
interface SimShop<T, C extends Runnable & Cloneable & Closeable> extends Shop<T> {
    C getSim(String name, String id);
}
