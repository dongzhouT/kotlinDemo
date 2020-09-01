package com.example.kotlindemo.hashmap;

import com.blankj.utilcode.util.GsonUtils;
import com.example.kotlindemo.generic.AppleShop;
import com.google.gson.Gson;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");
        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        int[] aa = {1, 2, 3};
        System.out.println(GsonUtils.toJson(aa));
        //
        Integer.highestOneBit(15);
        Hashtable<String, String> table = new Hashtable<>();
        table.put("1", "tablevalue1");
        table.put("2", "tablevalue2");
        table.put("3", "tablevalue3");
        for(Map.Entry entry:table.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        //HashMap Hashtable的区别
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("1", "name");
        linkedHashMap.put("2", "age");
        linkedHashMap.put("3", "weight");
        for(Map.Entry entry:linkedHashMap.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        Set<Integer> dataSet=new HashSet<>();
        dataSet.add(1);
        dataSet.add(2);
        dataSet.add(1);
        LinkedList<String> linkedList=new LinkedList();
        linkedList.add("a");
        linkedList.push("b");
        linkedList.peek();
        linkedList.poll();
        linkedList.removeLast();
        Queue<String> queue=new ArrayDeque<>();

    }
}
