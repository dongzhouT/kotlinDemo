package com.example.javalib.jvm;

import sun.misc.Unsafe;

class JvmDemo {
    public static void main(String[] args) {
//        String s1=new String("abc");
//        String s2=new String("abc");
//        System.out.println(s1==s2);//false
//        String s3="abc";
//        String s4="abc";
//        System.out.println(s3==s4);//true
//        System.out.println(s1==s3);//false
        String ss1=new StringBuilder().append("计算机").append("软件").toString();
        System.out.println(ss1.intern()==ss1);//true
        String ss2=new StringBuilder().append("ja").append("va").toString();
        System.out.println(ss2.intern()==ss2);//false
//        Unsafe.getUnsafe()
    }
}
