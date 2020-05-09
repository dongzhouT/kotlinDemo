package com.example.kotlindemo.view

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.dp
import com.example.kotlindemo.utils.getAvatar

private val BITMAP_SIZE = 200.dp
private val BITMAP_PADDING = 100.dp

class CanvasDemoView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val demoBitmap = getAvatar(context, BITMAP_SIZE.toInt())
    val radius = 100.dp
    val camera = Camera()

    init {
        camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
        camera.rotateX(30f)
    }

    override fun onDraw(canvas: Canvas) {
//        canvas.save()
//        canvas.translate(radius, radius)
//        canvas.rotate(45f, BITMAP_SIZE / 2, BITMAP_SIZE / 2)
//        canvas.drawBitmap(demoBitmap, 0f, 0f, paint)
//        canvas.restore()
        canvas.translate((BITMAP_PADDING + BITMAP_SIZE / 2), (BITMAP_PADDING + BITMAP_SIZE / 2))
        camera.applyToCanvas(canvas)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(demoBitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
    }
}