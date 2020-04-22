package com.example.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

private const val openAngle = 120
private val DASH_WIDTH = 2f.dp
private val DASH_LENGTH = 10f.dp
private val RADIUS = 140f.dp
private val LENGTH = 120f.dp
private val index = 5
private val num = 20

class DashBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dash = Path()
    private lateinit var pathMeasure: PathMeasure
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.addArc(width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                90 + openAngle / 2f,
                360f - openAngle)
        pathMeasure = PathMeasure(path, false)
    }

    init {
        paint.strokeWidth = 3f.dp;
        paint.style = Paint.Style.STROKE
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
//        paint.pathEffect=PathDashPathEffect.Style.ROTATE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
        paint.pathEffect = PathDashPathEffect(dash, (pathMeasure.length - DASH_WIDTH) / num, 0f, PathDashPathEffect.Style.ROTATE)
        canvas.drawPath(path, paint)
        paint.pathEffect = null
        canvas.drawLine(width / 2f, height / 2f,
                width / 2f + (LENGTH * cos(Math.toRadians((90 + openAngle / 2f + (360f - openAngle) / 20 * index).toDouble()))).toFloat(),
                height / 2f + (LENGTH * sin(Math.toRadians((90 + openAngle / 2f + (360f - openAngle) / 20 * index).toDouble()))).toFloat(),
                paint)
    }
}