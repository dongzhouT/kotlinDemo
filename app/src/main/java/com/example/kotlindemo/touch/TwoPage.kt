package com.example.kotlindemo.touch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.view.children
import kotlin.math.abs

/**
 * 实现一个只包含两页的简单的viewPager
 */
class TwoPage(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    var downX = 0f
    var downY = 0f
    var downScrollX = 0
    var scrolling = false
    var viewConfiguration = ViewConfiguration.get(context)
    var maxVelocity = viewConfiguration.scaledMaximumFlingVelocity
    var minVelocity = viewConfiguration.scaledMinimumFlingVelocity
    var pagingSlop = viewConfiguration.scaledPagingTouchSlop
    var velocityTracker = VelocityTracker.obtain()
    var scroller = OverScroller(context)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childTop = 0
        var childRight = width
        var childBottom = height
        for (child in children) {
            child.layout(childLeft, childTop, childRight, childBottom)
            childLeft += width
            childRight += width
        }
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        println("TwoPage== onInterceptTouchEvent=${event.actionMasked}")
        velocityTracker.addMovement(event)
        var result = false
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                scrolling = false
                downScrollX = scrollX
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                if (!scrolling) {
                    var dx = downX - event.x
                    if (abs(dx) > pagingSlop) {
                        scrolling = true
                        result = true
                        parent.requestDisallowInterceptTouchEvent(true)
                    }
                }
            }
        }
        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        println("TwoPage== onTouchEvent=${event.actionMasked}")
        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downScrollX = scrollX
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                println("scroll===${scrollX}")
                var dx = (downX - event.x + downScrollX).toInt()
                        .coerceAtLeast(0)
                        .coerceAtMost(width)
                scrollTo(dx, 0)
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                var vx = velocityTracker.xVelocity
                var scrollX = scrollX
                println("vx=${vx},minV=${minVelocity},maxV=${maxVelocity}")
                var targetPage = if (abs(vx) < minVelocity) {
                    if (scrollX > width / 2) 1 else 0
                } else {
                    if (vx < 0) 1 else 0
                }
                var scrollDistance = if (targetPage == 1) width - scrollX else -scrollX
                scroller.startScroll(scrollX, 0, scrollDistance, 0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX, 0)
            postInvalidateOnAnimation()
        }
    }
}