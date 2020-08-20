package com.example.javalib.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HashMapDemo {
    private <T> void fun(T t){

    }
    private <T  extends Map> void foo(T t){

    }
//    private <T super Integer> void foo(<T  super String> t){
//
//    }
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        for(int i=0;i<30;i++){
            map.put(i+"", "value"+i);
        }
        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
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
        for (int i = 0; i < 30; i++) {
            linkedHashMap.put(i+"", "LinkedHashMap:"+i);
        }
        for(Map.Entry entry:linkedHashMap.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        List<Object> list=new ArrayList<>();
        list.add(new String("ddd"));
        list.add(1);
        ClassLoader classLoader=ClassLoader.getSystemClassLoader();
        List<Integer> list1=new ArrayList<>();
    }
}
