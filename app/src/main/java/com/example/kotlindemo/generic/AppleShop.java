package com.example.kotlindemo.generic;

import com.example.kotlindemo.generic.Fruit.Fruit;

import java.util.ArrayList;
import java.util.List;

public class AppleShop<T extends Fruit> implements Shop<T> {
    @Override
    public T buy() {
        return null;
    }

    @Override
    public void refund(T data) {
        Enum

    }

    public <P> void merge(P item, List<P> list) {
        list.add(item);
    }
}
