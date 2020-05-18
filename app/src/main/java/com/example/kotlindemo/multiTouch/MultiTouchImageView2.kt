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
 * 合作型多点触控
 */
class MultiTouchImageView2(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var bitmap = getAvatar(context, 200.dp.toInt())
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val focusX: Float
        var focusY: Float
        var sumX = 0f
        var sumY = 0f
        var pointerCount = event.pointerCount
        var isPointerUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (i in 0 until event.pointerCount) {
            if (!(isPointerUp && i == event.actionIndex)) {
                sumX += event.getX(i)
                sumY += event.getY(i)
            }
        }
        if (isPointerUp) {
            pointerCount--
        }
        focusX = sumX / pointerCount
        focusY = sumY / pointerCount


        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                //记录按下点的坐标
                downX = focusX
                downY = focusY
                //记录图片按下时的位移
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }


            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + originalOffsetX
                offsetY = focusY - downY + originalOffsetY
                invalidate()
            }
        }
        return true
    }
}