package com.example.kotlindemo.hashmap;

import com.blankj.utilcode.util.GsonUtils;
import com.example.kotlindemo.generic.AppleShop;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Hashtable;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.get("key");
        int[] aa = {1, 2, 3};
        System.out.println(GsonUtils.toJson(aa));
        //
        Integer.highestOneBit(15);
        Hashtable<String, String> table = new Hashtable<>();
        table.put("key", "value");
        //HashMap Hashtable的区别
    }
}
