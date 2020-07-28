package com.example.kotlindemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.dp
import kotlin.math.cos
import kotlin.math.sin

private val ANGLES = floatArrayOf(30f, 60f, 120f, 120f, 30f)
private val COLORS = listOf<Int>(Color.RED, Color.DKGRAY, Color.GRAY, Color.BLUE, Color.YELLOW)
private val RADIUS = 100f.dp
private val offset = 10f.dp
private val selectedIndex = 3

class PieView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var startAngle = 0f;
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]
            if (index == selectedIndex) {
                canvas.save()
                canvas.translate((offset * cos(Math.toRadians((startAngle + angle / 2f).toDouble()))).toFloat(),
                        (offset * sin(Math.toRadians((startAngle + angle / 2f).toDouble()))).toFloat())
            }

            canvas.drawArc(width / 2f - RADIUS,
                    height / 2f - RADIUS,
                    width / 2f + RADIUS,
                    height / 2f + RADIUS,
                    startAngle,
                    angle,
                    true,
                    paint)
            startAngle += angle
            if (index == selectedIndex) {
                canvas.restore()
            }
        }
    }
}