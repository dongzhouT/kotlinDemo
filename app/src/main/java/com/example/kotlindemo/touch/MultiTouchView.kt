package com.example.kotlindemo.touch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlindemo.dp
import com.example.kotlindemo.utils.getAvatar

/**
 *
 */
class MultiTouchView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val bitmap = getAvatar(context, 200.dp.toInt())
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var offsetX = 0f
    private var offsetY = 0f
    private var originalOffsetX = 0f//初始偏移
    private var originalOffsetY = 0f
    private var downX = 0f//down事件触发时的位置
    private var downY = 0f
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = event.x - downX + originalOffsetX
                offsetY = event.y - downY + originalOffsetY
                invalidate()
            }
        }
        return true
    }
}