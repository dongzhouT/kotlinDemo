package com.example.kotlindemo.multiTouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlindemo.dp
import com.example.kotlindemo.utils.getAvatar

/**
 * 接力型多点触控
 */
class MultiTouchImageView1(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var bitmap = getAvatar(context, 200.dp.toInt())
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var actionId = 0
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//        println("x=${event.x},y=${event.y},event=${event.actionMasked}")
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                actionId = event.getPointerId(event.actionIndex)
                //记录按下点的坐标
                downX = event.getX(event.actionIndex)
                downY = event.getY(event.actionIndex)
                //记录图片按下时的位移
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                if (actionId == event.getPointerId(event.actionIndex)) {
                    if (event.actionIndex == event.pointerCount - 1) {
                        actionId = event.getPointerId(event.pointerCount - 2)
                    } else {
                        actionId = event.getPointerId(event.pointerCount - 1)
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = event.getX(event.findPointerIndex(actionId)) - downX + originalOffsetX
                offsetY = event.getY(event.findPointerIndex(actionId)) - downY + originalOffsetY
                invalidate()
            }
        }
        return true
    }
}