package com.example.kotlindemo.generic;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.kotlindemo.generic.Fruit.Apple;
import com.example.kotlindemo.generic.Fruit.Banana;
import com.example.kotlindemo.generic.Fruit.Fruit;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        //? extends 是一种约定 泛型的上届
        //？ super 设置下届
        Shop<? extends Fruit> shop = new Shop<Apple>() {
            @Override
            public Apple buy() {
                return null;
            }

            @Override
            public void refund(Apple data) {

            }
        };
        Apple apple = new Apple();
        Banana banana = new Banana();
        Fruit fruit = new Fruit() {
        };
//        shop.refund(apple);
//        shop.refund(fruit);
        //泛型类型擦除
        Fruit[] fruits = new Apple[10];
        fruits[0] = new Banana();
        ArrayList<? extends View> fruitList=new ArrayList<TextView>();
        ArrayList<? super AppCompatTextView> fruitsList2=new ArrayList<TextView>();

    }
}
