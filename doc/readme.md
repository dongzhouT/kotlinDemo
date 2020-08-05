# Handler原理 [参考](https://blog.csdn.net/AdobeSolo/article/details/75195394?utm_medium=distribute.wap_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase&depth_1-utm_source=distribute.wap_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase)
Handler是如何实现线程之间的切换的
现在有A、B两个线程，在A线程中有创建了handler，然后在B线程中调用handler发送一个message。

通过上面的分析我们可以知道，当在A线程中创建handler的时候，同时创建了`MessageQueue`与`Looper`，`Looper`在A线程中调用`loop`进入一个无限的for循环从`MessageQueue`中取消息，当B线程调用`handler`发送一个message的时候，会通过`enqueueMessage`将message插入到handler对应的`MessageQueue`中，`Looper`发现有`message`插入到`MessageQueue`中，取出message通过`msg.target.dispatchMessage(msg)`,执行相应的逻辑，因为Looper.loop()是在A线程中启动的，所以则回到了A线程，达到了从B线程切换到A线程的目的。
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
 1.直接使用已经建立的可用的(符合条件的)连接
 2.从连接池中找可用连接，不使用连接合并(routes) connection coalescing，不使用多路复用， Attempt to get a connection from the pool
 3.从连接池中找可用连接，使用连接合并(routes)，使用多路复用 This could match due to connection coalescing.
 4.建立连接 Do TCP + TLS handshakes
 5.从连接池中找可用连接，[只拿]多路复用，加了同步锁，防止创建多个相同连接，造成资源浪费
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