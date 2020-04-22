package com.example.kotlindemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View

private val RADIUS = 100f.dp

class TestView(context: Context?, attrs: AttributeSet?)
    : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    lateinit var pathMeasure: PathMeasure
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CCW)
        path.addRect(width/2f- RADIUS,height/2f,width/2f+ RADIUS,height/2f+ RADIUS*2,Path.Direction.CW)
        path.addCircle(width/2f,height/2f, RADIUS*1.5f,Path.Direction.CW)
        pathMeasure=PathMeasure(path,false)
        path.fillType=Path.FillType.INVERSE_EVEN_ODD
    }
}