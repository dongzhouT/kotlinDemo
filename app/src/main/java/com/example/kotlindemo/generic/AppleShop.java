package com.example.kotlindemo.generic;

import com.example.kotlindemo.generic.Fruit.Fruit;

import java.io.Serializable;
import java.util.List;

/**
 * 泛型
 *
 * @param <T>
 */
public class AppleShop<T extends Fruit> implements Shop<T> {
    @Override
    public T buy() {
        return null;
    }

    @Override
    public void refund(T data) {

    }

    //泛型方法
    @Override
    public <E> E trade(E item, int i) {
        return null;
    }

    @Override
    public <P extends Runnable & Serializable> void someMethod(P param) {

    }

    public <P> void merge(P item, List<P> list) {
        list.add(item);
    }

    public <k> k find(String item) {
        return null;
    }
}
