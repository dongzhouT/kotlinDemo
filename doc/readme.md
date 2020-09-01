# Handler原理 [参考](https://blog.csdn.net/AdobeSolo/article/details/75195394?utm_medium=distribute.wap_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase&depth_1-utm_source=distribute.wap_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase)
* looper循环的从MessageQueue中取message，message的target也就是handler通过dispatchMessage去分发处理消息
Handler是如何实现线程之间的切换的
现在有A、B两个线程，在A线程中有创建了handler，然后在B线程中调用handler发送一个message。

通过上面的分析我们可以知道，当在A线程中创建handler的时候，同时创建了`MessageQueue`与`Looper`，`Looper`在A线程中调用`loop`进入一个无限的for循环从`MessageQueue`中取消息，当B线程调用`handler`发送一个message的时候，会通过`enqueueMessage`将message插入到handler对应的`MessageQueue`中，`Looper`发现有`message`插入到`MessageQueue`中，取出message通过`msg.target.dispatchMessage(msg)`,执行相应的逻辑，因为Looper.loop()是在A线程中启动的，所以则回到了A线程，达到了从B线程切换到A线程的目的。
* HandlerThread
* IdleHandler
Idle handles only run if the queue is empty or if the first message
in the queue (possibly a barrier) is due to be handled in the future.
`Message msg = queue.next(); // might block`
延时msg：存的时候按时间先后顺序插入到messageQueue中，时间靠后的插在链表的后面，在looper取的时候,messageQueue.next()中阻塞线程，直到msg的when小于当前时间，去取出msg

* Message
Message.obtain() 从池子中取Message，节约资源。最大容量 int MAX_POOL_SIZE = 50
# Retrofit
```java
 OkHttpCall(RequestFactory requestFactory,Object[] args,okhttp3.Call.Factory callFactory,Converter<ResponseBody, T> responseConverter)

   HttpServiceMethod.java
   @Override
   final @Nullable ReturnT invoke(Object[] args) {
      Call<ResponseT> call = new OkHttpCall<>(requestFactory, args, callFactory, responseConverter);
      return adapt(call, args);
   }

 okhttp3.Call call = callFactory.newCall(requestFactory.create(args));
 ```
 **动态代理**，程序在运行时根据代理的配置实时创建新的类。
 **静态代理**，在不修改被代理对象的基础上，进行功能的扩展，需要共同实现某个接口或继承某个类。
 callFactory = new OkHttpClient();在 Retrofit build()中初始化
 requestFactory和args创建一个okhttp的request，然后调用okhttp3.enqueue发送网络请求
 responseConverter->addConverterFactory(GsonConverterFactory.create()) 用于请求结果的json格式解析
 addCallAdapterFactory 用来切线程，自带的call是切换到主线程
# OkHttp
* RealCall.getResponseWithInterceptorChain() 链式调用 拦截器
proceed（）intercept
* RetryAndFollowUpInterceptor 重试和跟踪 请求失败的重试和重定向
* BridgeInterceptor 桥接 content-type和gzip等预处理
* CacheInterceptor 缓存命中
* ConnectInterceptor 建立TCP或TLS连接，并创建对应的HttpCodec对象（HTTP1还是HTTP2）。
* findConnection 最多有五次建立连接，
1. 直接使用已经建立的可用的(符合条件的)连接
2. 从连接池中找可用连接，不使用连接合并(routes) connection coalescing，不使用多路复用， Attempt to get a connection from the pool
3. 从连接池中找可用连接，使用连接合并(routes)，使用多路复用 This could match due to connection coalescing.
4. 建立连接 Do TCP + TLS handshakes
5. 从连接池中找可用连接，[只拿]多路复用，加了同步锁，防止创建多个相同连接，造成资源浪费
CallServerInterceptor 去请求与相应的I/O操作，发请求读响应

# view绘制流程
* ActivityThread.handleLaunchActivity
通过反射创建Activity
调用activity.attach()方法，
 * 此处Activity.mWindow=new PhoneWindow() //setContentView():activity通过phoneWindow和view关联
 * attach()的时候创建`mWindowManager`实现类是`WindowManagerImpl`
调用`activity.onCreate()`方法，加载布局`onCreate()setContentView()->phoneWindow().setContentView()`:
      *  installDecor创建`decorView`: new DecorView()和将framework的布局加载到decorView中
      *  将布局文件加载到content中：mLayoutInflater.inflate(layoutResID, mContentParent)
*  ActivityThread.handleResumeActivity
  * 调用Activity.performResume().onResume()方法
  * `mWindowManager`.addView()中WindowManagerGlobal(单例).addView()创建`viewRootImpl`，此时创建了`mThread`,`mAttachInfo`
 * addView()中调用了setView()->ViewRootImpl.performTraversals,执行decorView的measure，layout,draw方法

*DecorView的parent是 `viewRootImpl`,`viewRootImpl`中checkThread()会判断操作UI是否是主线程([mThread]!=Thread.currentThread())*

# view触摸反馈原理
event.action不支持多点
event.actionMasked支持多点触控
**View**
dispatchTouchEvent
onTouchEvent
```
public boolean dispatchTouchEvent(MotionEvent event) {
    return onTouchEvent(event)
}
```
**viewGroup**
dispatchTouchEvent
onInterceptTouchEvent(viewGroup特有方法)
onTouchEvent
```
public boolean dispatchTouchEvent(MotionEvent event) {
    boolean result;
    if(interceptTouchEvent()){
        result=onTouchEvent();super.dispatchTouchEvent
    }else{
        result=子view的dispatchTouchEvent;
    }
    return result;
}
```
# 触摸反馈工具
*GestureDetectorCompat 处理双击
*overScroller 处理惯性滑动
*ScaleGestureDetector 处理双指缩放
# 多点触控 event:MotionEvent
`override fun onTouchEvent(event: MotionEvent): Boolean`
**event.actionMasked**
简化数据结构 point(x,y,index,id)
index 用于遍历手指
id 用于追踪某个手指
* event.pointerCount 手指总数 >=1
* event.getPointerId(index) 获得index
* event.findPointerIndex(id) 获得id
* event.getX(index) 获得某个手指的坐标
* event.actionIndex 引起ACTION_POINTER_DOWN和ACTION_POINTER_UP发生的那个手指的index
-当发生ACTION_POINTER_UP,pointerCount不会变化, ACTION_POINTER_DOWN会导致pointerCount+1
## touch事件发生序列
```
MotionEvent.ACTION_DOWN point(x,y,index,id)
MotionEvent.ACTION_MOVE point(x,y,index,id)
MotionEvent.ACTION_MOVE point(x,y,index,id)
MotionEvent.ACTION_POINTER_DOWN point(x,y,index,id) point(x,y,index,id)
MotionEvent.ACTION_MOVE
MotionEvent.ACTION_POINTER_DOWN
MotionEvent.ACTION_MOVE
MotionEvent.ACTION_POINTER_UP
MotionEvent.ACTION_MOVE
MotionEvent.ACTION_POINTER_UP
MotionEvent.ACTION_MOVE
MotionEvent.ACTION_UP
```
# viewPager
## VelocityTracker
* 声明 private val velocityTracker = VelocityTracker.obtain()
* ACTION_DOWN的时候velocityTracker.clear()
* 每次onTouchEvent调用velocityTracker.addMovement(event)
* ACTION_UP的时候调用velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat()),获得xVelocity
## overscroller
* 声明 OverScroller(context)
* 用于计算overscroller.startScroll(scrollX, 0, scrollDistance, 0)
* 判断计算有没有结束 overscroller.computeScrollOffset()，true:没有结束
* 获取当前值 scrollTo(overscroller.currX, overscroller.currY)
```
父view的 on InterceptTouchEvent+ACTION_DOWN
子view的  ACTION_DOWN,actionIdex=0,pointCount=1,0
父view的 on InterceptTouchEvent+ACTION_MOVE
子view的  ACTION_MOVE,actionIdex=0,pointCount=1,0
...
子view的  ACTION_CANCEL,actionIdex=0,pointCount=1,0
父view的 onTouchEvent+ACTION_MOVE
...
父view的 onTouchEvent+ACTION_UP
```
#拖拽API
## OnDragListener
	* `view.startDrag(ClipData data, DragShadowBuilder shadowBuilder,Object myLocalState, int flags)`
	* child.setOnDragListener() fun onDrag(v: View, event: DragEvent)
```
    private inner class MyDragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    if (v === targetView) {
                        v.visibility = View.INVISIBLE
                    }
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    if (v !== targetView)
                        sort(v)
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    if (v === targetView) {
                        v.visibility = visibility
                    }
                }
            }
            return true

        }
    }
```
**本质是在原view上绘制一个图层，拖拽的是图层，可以跨进程传递数据，实际view没有动**
## ViewDragHelper
	* 初始化 `private val viewDragHelper = ViewDragHelper.create(this, DragCallback())`
	* 接管父类方法：
	```
	override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(ev)
    }
	override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper.processTouchEvent(event)
        return true
    }
	```
	* DragCallback
	```
	private inner class DragCallback : ViewDragHelper.Callback() {
        var capturedLeft = 0f
        var capturedTop = 0f

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }
        //钳制水平方向的移动，默认返回0，及水平方向不能移动
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            capturedChild.elevation = elevation + 1
            capturedLeft = capturedChild.left.toFloat()
            capturedTop = capturedChild.top.toFloat()
        }

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            dragHelper.settleCapturedViewAt(capturedLeft.toInt(), capturedTop.toInt())
            postInvalidateOnAnimation()
        }
    }
	```
**本质是改变的view的left，top，right，bottom**
# java多线程和线程安全
## Thread ExecutorService
Thread,Runnable 不建议使用，不利于线程管理
Executor线程池
```
ExecutorService service = Executors.newCachedThreadPool();
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue)
```
## volatile
每个线程有自己的内部存储，更新后不会实时通知共享内存，主线程更新数据后，子线程不会实时更新
* 保证变量的可见性和同步性，对基本数据类型的原子性。降低效率，提高数据安全。
* `对象`的原子性和同步性，可使用AtomicReference
* 使用场景，多线程操作同一个数据
```private volatile int count=0;```
## synchronized
本质：保证代码块内资源的互斥访问，即同一时间，由同一个monitor监视的代码，最多只能有一个线程在访问
```
Object monitor = new Object();//监视器
synchronized(monitor){
    //...
}
静态方法加锁，monitor也要使用静态变量
private static void fun(){
    synchronized(Single.class){
        //...
    }
}
```
## 死锁
多重锁可能导致死锁，单锁不会出现死锁
```
    private void fun1(){
        synchronized(monitor1){
            x++;
            synchronized(monitor2){
                y++;
            }
        }
    }
    private void fun2(){
            synchronized(monitor2){
                y++;
                synchronized(monitor1){
                    x++;
                }
            }
        }

```
## 乐观锁
乐观并发控制，读数据不锁，写数据加锁。假设别人不会频繁的读写，性能有优势
## 悲观锁
悲观并发控制，读数据就加锁，锁的时间长，读写非常频繁的时候使用，降低性能，提高数据安全
## ReentrantLock ReentrantReadWriteLock
可重入锁 ，手动加锁
应用：ArrayBlockingQueue
```
    lock.lock()
    try{
        //...
    }finally{
        lock.unLock();
    }
```
** 线程安全的本质：多个线程访问共同资源，对该资源的读写发生冲突，导致数据错误 **
# 单例模式
## 饿汉
```
public class SingleMan{
    private static SingleMan instance = new SingleMan();
    private SingleMan(){}
    public static SingleMan getInstance(){
        return instance;
    }
}
```
线程安全
程序初始化的时候就创建对象，有可能用不到，造成资源浪费。
## 懒汉
```
public class SingleMan{
    private static SingleMan instance;
    private SingleMan(){}
    public static synchronized SingleMan getInstance(){
        if(instance == null){
            instance = new SingleMan();
        }
        return instance;
    }
}
```
线程安全，有同步锁，效率低。

## 饱汉
```
public class SingleMan{
    private static volatile SingleMan instance;
    private SingleMan(){}
    public static SingleMan getInstance(){
        if(instance == null){
            synchronized(SingleMan.class){
                if(instance == null){
                    instance = new SingleMan();
                }
            }
        }
        return instance;
    }
}
```
双重检查
# 线程间通信
## 终止线程
* `thread.stop()`
终止线程
* `thread.interrupt()`
打断，把线程标记为终止状态，不是强制终止。线程不立即结束，可以在线程中手动判断isInterrupted标记是否终止
isInterrupted() 判断线程是否需要结束
Thread.interrupted() 线程内终止并修改终止标记
如果线程正在sleep(),会抛出InterruptedException异常
* `Object.wait()` 释放同步锁，将线程加入等待区(挂起),配合notify()使用
* `Object.notify()/Object.notifyAll()` 通知一个/所有等待区线程重新加入锁竞争
** wait()和notify()必须放在synchronized代码块中 **
* `thread.join()` 将子线程插入到当前线程，阻塞当前线程
* `thread.yield()` 在线程里面调用，让出自己的时间片，暂停当前线程，给和自己同优先级的线程
* 会引起InterruptedException的方法，Thread.sleep(),Object.wait(),thread.join()

# Activity启动流程
```
Activity.startActivity(intent)
Instrumentation.execStartActivity()
IActivityTaskManager.getService().startActivity()
//AMS
public class ActivityManagerService extends IActivityManager.Stub
        implements Watchdog.Monitor, BatteryStatsImpl.BatteryCallback 
ActivityManagerService.startActivity()
//管理Activity
ActivityTaskManagerService.startActivity()->
{  
//ActivityStarter
return getActivityStartController().obtainStarter(intent, "startActivityAsUser")
                .setCaller(caller)
                .setCallingPackage(callingPackage)
                .setResolvedType(resolvedType)
                .setResultTo(resultTo)
                .setResultWho(resultWho)
                .setRequestCode(requestCode)
                .setStartFlags(startFlags)
                .setProfilerInfo(profilerInfo)
                .setActivityOptions(bOptions)
                .setMayWait(userId)
                .execute();
}
/**
 * Controller for interpreting how and then launching an activity.
 *
 * This class collects all the logic for determining how an intent and flags should be turned into
 * an activity and associated task and stack.
 */
交给 ActivityStarter
```
# RxJava
被观察值：Observable,Single
## Single
```
 Single.just(2)
                .delay(1,TimeUnit.SECONDS)
                .map(object :Function<Int,String>{
                    override fun apply(t: Int?): String {
                        return t.toString()
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SingleObserver<String> {
                    override fun onSuccess(t: String?) {
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })
```
## Observable
```
Observable.interval(0, 1, TimeUnit.SECONDS)
                .delay(1,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long?> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onNext(t: Long?) {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })
```
关闭订阅，disposable.dispose()
## 线程切换
* subscribeOn(Schedulers.io())，本质用的是`ScheduledExecutorService`,
`final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1, factory);`
* newThread()新开线程;io()用pool，实现线程复用
* observeOn(AndroidSchedulers.mainThread()),本质用的是`new Handler(Looper.getMainLooper()),handler.sendMessageDelayed()`

# LeakCanary
四种引用：强引用，弱引用，软引用，虚引用
```
    var leakView = ArrayList<View>()//强引用
    var weak = WeakReference<User>(User())//弱引用，可以被GC回收
    var soft = SoftReference<User>(User())//软引用 内存不⾜会被垃圾回收
    var phantom = PhantomReference<User>(User(), ReferenceQueue())//虚引用  不能通过 get() 获得引⽤对象,会被垃圾回收
    fun main() {
        var user = weak.get()
        var softUser = soft.get()
        var phan = phantom.get()//return null
    }
```
2.X版本不需要手动调用install
* 通过`application.registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback)`绑定Activity生命周期回调
* AppWatcher.objectWatcher.watch(User(), "") `KeyedWeakReference`
* GcTrigger.runGc() 实际是调用`Runtime.getRuntime.gc()`触发GC 为什么不用system.gc() System.gc() does not garbage collect every time. Runtime.gc() is more likely to perform a gc.
* `AndroidHeapDumper.dumpHeap()发送通知->HeapAnalyzerService.runAnalysis(application, heapDumpFile)` 启动一个`HeapAnalyzerService`继承自IntentService，加载并分析`.hprof`文件
# 构建流程
1. 编译资源 使用aapt2工具对资源进行编译，生成.flat文件
2. 链接资源 使用aapt2工具将资源整合，生成资源文件和R.java文件
3. 编译Java文件 使用 javac 工具编译java文件，->.class 字节码文件
4. dex编译 使用d8工具编译 class 代码，->dex文件
5. 合并dex文件和资源文件，使用zip命令合并资源文件和代码文件，->还未签名的apk文件
6. 签名 使用apksigner工具对apk签名 ->已签名的apk文件
```
https://www.jianshu.com/p/a2c644f15790
图中的矩形表示用到或者生成的文件，椭圆表示工具。
1\. 通过aapt打包res资源文件，生成R.java、resources.arsc和res文件
2\. 处理.aidl文件，生成对应的Java接口文件
3\. 通过Java Compiler编译R.java、Java接口文件、Java源文件，生成.class文件
4\. 通过dex命令，将.class文件和第三方库中的.class文件处理生成classes.dex
5\. 通过apkbuilder工具，将aapt生成的resources.arsc和res文件、assets文件和classes.dex一起打包生成apk
6\. 通过Jarsigner工具，对上面的apk进行debug或release签名
7\. 通过zipalign工具，将签名后的apk进行对齐处理。
这样就得到了一个可以安装运行的Android程序。
```
# classLoader
## 双亲委派模型
如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委派给父类加载器去完成，
每一个层次的类加载器都是如此，因此所有的加载请求最终都应该传送到顶层的启动类加载器中，
只有当父加载器反馈自己无法完成这个加载请求（它的搜索范围中没有找到所需的类）时，子加载器才会尝试自己去加载。

