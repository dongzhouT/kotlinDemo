package com.example.kotlindemo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.GsonUtils
import com.example.kotlindemo.dp

private var RADIUS = 100.dp
private var LINE_WIDTH = 20.dp

class SportView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val fontMetrics = Paint.FontMetrics()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 60.dp
    }
    private val boundsf = RectF(0f.dp, 0f.dp, 150f.dp, 150f.dp)
    private val bounds = Rect()

    init {
        paint.getFontMetrics(fontMetrics)
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = Color.parseColor("#90A4AE")
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = LINE_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = Color.parseColor("#ff4081")
        canvas.drawArc(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS, height / 2f + RADIUS,
                -90f, 120f, false, paint)

        paint.color = Color.parseColor("#ff4081")
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
        //用于静态文字
        paint.getTextBounds("aabb", 0, "aabb".length, bounds)
        canvas.drawText("aabb", width / 2f, height / 2f - (bounds.top + bounds.bottom) / 2, paint);
//        canvas.drawText("aabb", width / 2f, height / 2f - (fontMetrics.descent + fontMetrics.ascent) / 2, paint)
        println("fontmetric====>${GsonUtils.toJson(fontMetrics)}")
        println("fontmetric====>${GsonUtils.toJson(bounds)}")
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("abab", 0f, -fontMetrics.top, paint)
    }


}