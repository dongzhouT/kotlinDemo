package com.example.kotlindemo.recyclerview

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_recyclerview_demo.*
import kotlin.concurrent.thread

/**
 * view绘制流程
 * ActivityThread.handleLaunchActivity
 * 通过反射创建Activity
 * 调用activity.attach()方法，
 *   ->此处Activity.mWindow=new PhoneWindow() //setContentView():activity通过phoneWindow和view关联
 *   ->attach()的时候创建[mWindowManager]实现类是WindowManagerImpl
 * 调用activity.onCreate()方法，加载布局
 *   onCreate()setContentView()->phoneWindow().setContentView()->
 *       ->installDecor创建decorView: new DecorView()和将framework的布局加载到decorView中
 *       ->将布局文件加载到content中：mLayoutInflater.inflate(layoutResID, mContentParent)
 *  ActivityThread.handleResumeActivity
 *   ->调用Activity.performResume().onResume()方法
 *   ->[mWindowManager].addView()中WindowManagerGlobal(单例).addView()创建viewRootImpl，此时创建了[mThread],mAttachInfo
 *   ->->addView()中调用了setView()->ViewRootImpl.performTraversals,执行decorView的measure，layout,draw方法
 *  //DecorView的parent是 viewRootImpl,viewRootImpl中checkThread()会判断操作UI是否是主线程([mThread]!=Thread.currentThread())
 *
 */
class RecyclerviewDemoActivity : Activity() {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_demo)
        rv.layoutManager = LinearLayoutManager(this)
        val dataList = listOf<String>("aa", "bb", "cc", "dd", "ee")
        var adapter = ItemAdapter(dataList)
        rv.adapter = adapter
        tv_click.setOnClickListener {
            //            tv_click.text = "clicked"
            println("window.decorView.parent=${window.decorView.parent.toString()}")
            it.requestLayout()
            thread {
                //                Thread.sleep(2000)
                tv_click.text = "onCreate"
            }
        }
    }
}
