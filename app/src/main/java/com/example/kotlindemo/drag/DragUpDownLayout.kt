package com.example.kotlindemo.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.customview.widget.ViewDragHelper
import kotlinx.android.synthetic.main.activity_drag_up_down.view.*
import kotlin.math.abs

class DragUpDownLayout(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private val viewConfiguration = ViewConfiguration.get(context)
    private var viewDragHelper = ViewDragHelper.create(this, DragCallback())
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper.processTouchEvent(event)
        return true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            postInvalidateOnAnimation()
        }
    }

    inner class DragCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            println("onViewReleased:yvel=$yvel")
            if (abs(yvel) > viewConfiguration.scaledMinimumFlingVelocity) {
                if (yvel < 0) {
                    viewDragHelper.settleCapturedViewAt(0, 0)
                } else {
                    viewDragHelper.settleCapturedViewAt(0, height - layoutDrag.height)
                }
            } else {
                if (releasedChild.top < height - releasedChild.bottom) {
                    viewDragHelper.settleCapturedViewAt(0, 0)
                } else {
                    viewDragHelper.settleCapturedViewAt(0, height - layoutDrag.height)
                }
            }
            postInvalidateOnAnimation()

        }

    }
}