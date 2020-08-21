package com.example.kotlindemo.activity;

import java.util.ArrayList;
import java.util.List;

/**
 * $ javap -v Hello.class
 * Classfile /D:/Users/Administrator/AndroidStudioProjects/KotlinDemo/app/src/main/java/com/example/kotlindemo/activity/Hello.class
 *   Last modified 2020-8-21; size 539 bytes
 *   MD5 checksum c7eaff1f1f753d5abcff755b18896e0c
 *   Compiled from "Hello.java"
 * public class com.example.kotlindemo.activity.Hello
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #8.#22         // java/lang/Object."<init>":()V
 *    #2 = Class              #23            // java/util/ArrayList
 *    #3 = Methodref          #2.#22         // java/util/ArrayList."<init>":()V
 *    #4 = Fieldref           #5.#24         // com/example/kotlindemo/activity/Hello.a:Ljava/util/List;
 *    #5 = Class              #25            // com/example/kotlindemo/activity/Hello
 *    #6 = Methodref          #5.#22         // com/example/kotlindemo/activity/Hello."<init>":()V
 *    #7 = Methodref          #5.#26         // com/example/kotlindemo/activity/Hello.addSum:()V
 *    #8 = Class              #27            // java/lang/Object
 *    #9 = Utf8               a
 *   #10 = Utf8               Ljava/util/List;
 *   #11 = Utf8               Signature
 *   #12 = Utf8               Ljava/util/List<Ljava/lang/String;>;
 *   #13 = Utf8               <init>
 *   #14 = Utf8               ()V
 *   #15 = Utf8               Code
 *   #16 = Utf8               LineNumberTable
 *   #17 = Utf8               addSum
 *   #18 = Utf8               main
 *   #19 = Utf8               ([Ljava/lang/String;)V
 *   #20 = Utf8               SourceFile
 *   #21 = Utf8               Hello.java
 *   #22 = NameAndType        #13:#14        // "<init>":()V
 *   #23 = Utf8               java/util/ArrayList
 *   #24 = NameAndType        #9:#10         // a:Ljava/util/List;
 *   #25 = Utf8               com/example/kotlindemo/activity/Hello
 *   #26 = NameAndType        #17:#14        // addSum:()V
 *   #27 = Utf8               java/lang/Object
 * {
 *   java.util.List<java.lang.String> a;
 *     descriptor: Ljava/util/List;
 *     flags:
 *     Signature: #12                          // Ljava/util/List<Ljava/lang/String;>;
 *
 *   public com.example.kotlindemo.activity.Hello();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=3, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: aload_0
 *          5: new           #2                  // class java/util/ArrayList
 *          8: dup
 *          9: invokespecial #3                  // Method java/util/ArrayList."<init>":()V
 *         12: putfield      #4                  // Field a:Ljava/util/List;
 *         15: return
 *       LineNumberTable:
 *         line 64: 0
 *         line 65: 4
 *
 *   public void addSum();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=4, args_size=1
 *          0: iconst_0
 *          1: istore_1
 *          2: iload_1
 *          3: iinc          1, 1
 *          6: istore_2
 *          7: iinc          1, 1
 *         10: iload_1
 *         11: istore_3
 *         12: return
 *       LineNumberTable:
 *         line 68: 0
 *         line 69: 2
 *         line 70: 7
 *         line 71: 12
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=2, args_size=1
 *           创建一个对象, 并将其引用引用值压入栈顶
 *          0: new           #5                  // class com/example/kotlindemo/activity/Hello
 *          复制栈顶数值并将复制值压入栈顶
 *          3: dup
 *          调用超类构建方法, 实例初始化方法, 私有方法
 *          4: invokespecial #6                  // Method "<init>":()V
 *          将栈顶引用型数值存入第二个本地变量
 *          7: astore_1
 *          将第二个引用类型本地变量推送至栈顶
 *          8: aload_1
 *          调用实例方法
 *          9: invokevirtual #7                  // Method addSum:()V
 *         从当前方法返回void
 *         12: return
 *       LineNumberTable:
 *         line 74: 0
 *         line 75: 8
 *         line 76: 12
 * }
 * SourceFile: "Hello.java"
 */
public class Hello {
    List<String> a = new ArrayList<>();

    public void addSum() {
        int kkk = 0;
        int a = kkk++;
        int b = ++kkk;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.addSum();
    }
}
