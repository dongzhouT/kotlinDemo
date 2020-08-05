package com.example.kotlindemo.touch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.view.children
import com.example.kotlindemo.touch.TouchView.Companion.TAG
import kotlin.math.abs

class TwoPager2(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    val viewConfiguration = ViewConfiguration.get(context)
    private val velocityTracker = VelocityTracker.obtain()
    private val minSlop = viewConfiguration.scaledPagingTouchSlop
    private val minVelocity = viewConfiguration.scaledMinimumFlingVelocity
    private val maxVelocity = viewConfiguration.scaledMaximumFlingVelocity
    private val overscroller = OverScroller(context)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private var originalX = 0
    private var downX = 0f
    private var downY = 0f
    private var scrolling = false
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childRight = width
        var childTop = 0
        var childBottom = height
        for (child in children) {
            child.layout(childLeft, childTop, childRight, childBottom)
            childLeft += width
            childRight += width
        }
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
//        if (event.actionMasked != MotionEvent.ACTION_MOVE) {
            println("${TAG}  onInterceptTouchEvent+${MotionEvent.actionToString(event.actionMasked)}")
//        }
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        var result = false
        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                originalX = scrollX
                result = false
                scrolling = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (!scrolling) {
                    var dx = downX - event.x
                    if (abs(dx) > minSlop) {
                        scrolling = true
                        result = true
//                        parent.requestDisallowInterceptTouchEvent(true)
                    }
                }
            }
        }

        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//        if (event.actionMasked != MotionEvent.ACTION_MOVE) {
            println("$TAG onTouchEvent+${MotionEvent.actionToString(event.actionMasked)}")
//        }
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                originalX = scrollX
            }
            MotionEvent.ACTION_MOVE -> {
                var dx = (downX - event.x + originalX).toInt()
                        .coerceAtLeast(0)
                        .coerceAtMost(width)
                scrollTo(dx, 0)
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                var vx = velocityTracker.xVelocity
                val scrollX = scrollX
                println("vx=${vx},scrollx=$scrollX")
                var targetPage = if (abs(vx) < minVelocity) {
                    //超过一半，显示第二屏
                    if (scrollX > width / 2) 1 else 0
                } else {
                    //注意边界判断
                    if (vx < 0) 1 else 0
                }
                var scrollDistance = if (targetPage == 1) width - scrollX else -scrollX
                overscroller.startScroll(scrollX, 0, scrollDistance, 0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    /**
     * 利用系统的回调方法
     */
    override fun computeScroll() {
        //没有完成返回true
        if (overscroller.computeScrollOffset()) {
            scrollTo(overscroller.currX, overscroller.currY)
            postInvalidateOnAnimation()
        }
    }
}