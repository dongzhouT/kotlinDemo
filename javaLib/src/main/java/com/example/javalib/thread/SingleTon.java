package com.example.javalib.thread;

/**
 * $ javap -v SingleTon.class
 * Classfile /D:/Users/Administrator/AndroidStudioProjects/KotlinDemo/javaLib/src/m                                                                                                                                  ain/java/com/example/javalib/thread/SingleTon.class
 *   Last modified 2020-8-21; size 590 bytes
 *   MD5 checksum 11a22453641cf93e9e00b06092907390
 *   Compiled from "SingleTon.java"
 * public class com.example.javalib.thread.SingleTon
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #5.#20         // java/lang/Object."<init>":()V
 *    #2 = Fieldref           #3.#21         // com/example/javalib/thread/SingleTo                                                                                                                                  n.mInstance:Lcom/example/javalib/thread/SingleTon;
 *    #3 = Class              #22            // com/example/javalib/thread/SingleTo                                                                                                                                  n
 *    #4 = Methodref          #3.#20         // com/example/javalib/thread/SingleTo                                                                                                                                  n."<init>":()V
 *    #5 = Class              #23            // java/lang/Object
 *    #6 = Utf8               mInstance
 *    #7 = Utf8               Lcom/example/javalib/thread/SingleTon;
 *    #8 = Utf8               <init>
 *    #9 = Utf8               ()V
 *   #10 = Utf8               Code
 *   #11 = Utf8               LineNumberTable
 *   #12 = Utf8               getInstance
 *   #13 = Utf8               ()Lcom/example/javalib/thread/SingleTon;
 *   #14 = Utf8               StackMapTable
 *   #15 = Class              #23            // java/lang/Object
 *   #16 = Class              #24            // java/lang/Throwable
 *   #17 = Utf8               <clinit>
 *   #18 = Utf8               SourceFile
 *   #19 = Utf8               SingleTon.java
 *   #20 = NameAndType        #8:#9          // "<init>":()V
 *   #21 = NameAndType        #6:#7          // mInstance:Lcom/example/javalib/thre                                                                                                                                  ad/SingleTon;
 *   #22 = Utf8               com/example/javalib/thread/SingleTon
 *   #23 = Utf8               java/lang/Object
 *   #24 = Utf8               java/lang/Throwable
 * {
 *   public static com.example.javalib.thread.SingleTon getInstance();
 *     descriptor: ()Lcom/example/javalib/thread/SingleTon;
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=2, args_size=0
 *          0: getstatic     #2                  // Field mInstance:Lcom/example/ja                                                                                                                                  valib/thread/SingleTon;
 *          3: ifnonnull     37
 *          6: ldc           #3                  // class com/example/javalib/threa                                                                                                                                  d/SingleTon
 *          8: dup
 *          9: astore_0
 *         10: monitorenter
 *         11: getstatic     #2                  // Field mInstance:Lcom/example/ja                                                                                                                                  valib/thread/SingleTon;
 *         14: ifnonnull     27
 *         17: new           #3                  // class com/example/javalib/threa                                                                                                                                  d/SingleTon
 *         20: dup
 *         21: invokespecial #4                  // Method "<init>":()V
 *         24: putstatic     #2                  // Field mInstance:Lcom/example/ja                                                                                                                                  valib/thread/SingleTon;
 *         27: aload_0
 *         28: monitorexit
 *         29: goto          37
 *         32: astore_1
 *         33: aload_0
 *         34: monitorexit
 *         35: aload_1
 *         36: athrow
 *         37: getstatic     #2                  // Field mInstance:Lcom/example/ja                                                                                                                                  valib/thread/SingleTon;
 *         40: areturn
 *       Exception table:
 *          from    to  target type
 *             11    29    32   any
 *             32    35    32   any
 *       LineNumberTable:
 *         line 10: 0
 *         line 11: 6
 *         line 12: 11
 *         line 13: 17
 *         line 15: 27
 *         line 17: 37
 *       StackMapTable: number_of_entries = 3
 *         frame_type = 252 /* append
 *offset_delta=27
         *locals=[

*class java/lang/Object]
        *frame_type=68 /* same_locals_1_stack_item
        *stack=[

*class java/lang/Throwable]
        *frame_type=250 /* chop
        *offset_delta=4
        *
        *static {};
        *descriptor:()V
        *flags:ACC_STATIC
        *Code:
        *stack=1,locals=0,args_size=0
        *0:aconst_null
        *1:putstatic     #2                  // Field mInstance:Lcom/example/ja                                                                                                                                  valib/thread/SingleTon;
        *4:return
        *LineNumberTable:
        *line 4:0
        *}
        *SourceFile:"SingleTon.java"
 *
 *
 *
 */
public class SingleTon {
    private static SingleTon mInstance = null;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (mInstance == null) {
            synchronized (SingleTon.class) {
                if (mInstance == null) {
                    mInstance = new SingleTon();
                }
            }
        }
        return mInstance;
    }
}
