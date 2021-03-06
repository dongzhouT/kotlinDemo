package com.example.kotlindemo.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.dp

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var radius = 50f.dp
        set(value) {
            field = value
            invalidate()
        }
    init {
        setLayerType(LAYER_TYPE_HARDWARE,null)
    }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }

}