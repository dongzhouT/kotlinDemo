package com.example.kotlindemo.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper
import androidx.customview.widget.ViewDragHelper.Callback

class DragHelperGridView2(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val COLUMNS = 2
    private val ROWS = 3
    private val viewDragHelper = ViewDragHelper.create(this, DragCallback())
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var specWidth = MeasureSpec.getSize(widthMeasureSpec)
        var specHeight = MeasureSpec.getSize(heightMeasureSpec)
        var childWidth = specWidth / COLUMNS
        var childHeight = specHeight / ROWS
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY))
        setMeasuredDimension(specWidth, specHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft: Int
        var childTop: Int
        var childWidth: Int = width / COLUMNS
        var childHeight: Int = height / ROWS
        for ((index, child) in children.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper.processTouchEvent(event)
        return true
    }

    private inner class DragCallback : Callback() {
        private var capturedLeft = 0f
        private var capturedTop = 0f
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            capturedChild.elevation = elevation + 1
            capturedLeft = capturedChild.left.toFloat()
            capturedTop = capturedChild.top.toFloat()
            super.onViewCaptured(capturedChild, activePointerId)
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            viewDragHelper.settleCapturedViewAt(capturedLeft.toInt(), capturedTop.toInt())
            ViewCompat.postInvalidateOnAnimation(this@DragHelperGridView2)
        }

    }

    override fun computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }


}