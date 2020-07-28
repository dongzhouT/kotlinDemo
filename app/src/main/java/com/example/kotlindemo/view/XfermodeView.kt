package com.example.kotlindemo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.dp

private val IMG_WIDTH = 150f.dp
private val xfmode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)

class XfermodeView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(0f.dp, 0f.dp, 150f.dp, 150f.dp)
    private val circleBitmap = Bitmap.createBitmap(IMG_WIDTH.toInt(), IMG_WIDTH.toInt(), Bitmap.Config.ARGB_8888)
    private val squareBitmap = Bitmap.createBitmap(IMG_WIDTH.toInt(), IMG_WIDTH.toInt(), Bitmap.Config.ARGB_8888)

    init {
        var canvas = Canvas(circleBitmap)
        paint.color = Color.parseColor("#ff0000")
        canvas.drawOval(50f.dp, 0f, 150f.dp, 100f.dp, paint)
        paint.color = Color.parseColor("#0000ff")
        canvas.setBitmap(squareBitmap)
        canvas.drawRect(0f, 50f.dp, 100f.dp, 150f.dp, paint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var count = canvas.saveLayer(bounds, null)
        canvas.drawBitmap(circleBitmap, 0f.dp, 0f.dp, paint)
        paint.xfermode = xfmode
        canvas.drawBitmap(squareBitmap, 0f.dp, 0f.dp, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)
    }


}