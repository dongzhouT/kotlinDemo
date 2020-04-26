package com.example.kotlindemo.animator

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.dp

class PointFView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var point = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 20.dp
        strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPoint(point.x, point.y, paint)
    }

}

class PointType : TypeEvaluator<PointF> {
    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
        return PointF(startValue.x + (endValue.x - startValue.x) * fraction, startValue.y + (endValue.y - startValue.y) * fraction)
    }

}
