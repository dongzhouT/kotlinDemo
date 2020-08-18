package com.example.javalib.hashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapDemo {
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
    }
}
