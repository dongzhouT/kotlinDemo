package com.example.kotlindemo.generic;

import java.io.Serializable;
import java.util.List;

public interface Shop<T> {
    T buy();

    void refund(T data);

    <E> E trade(E item, int i);

    <P extends Runnable & Serializable> void someMethod(P param);

}
