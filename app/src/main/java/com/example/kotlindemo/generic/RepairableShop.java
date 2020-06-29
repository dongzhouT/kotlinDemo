package com.example.kotlindemo.generic;

public interface RepairableShop<M> extends Shop<M>{
    void repair(M item);
}
