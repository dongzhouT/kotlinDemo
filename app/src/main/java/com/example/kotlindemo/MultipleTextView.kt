package com.example.kotlindemo

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

private val IMAGE_SIZE = 150.dp
private val IMAGE_PADDING = 50.dp

class MultipleTextView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val txt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In iaculis sagittis " +
            "porttitor. Nunc interdum turpis hendrerit ex lobortis ornare et venenatis magna. " +
            "Suspendisse vel feugiat mi. Proin lacinia turpis risus, sit amet interdum purus " +
            "pretium commodo. Nulla erat ante, imperdiet sed dapibus sit amet, faucibus et nisl." +
            " Donec eu fermentum sapien, eget luctus massa. Nulla dignissim ipsum feugiat dolor mattis," +
            " sit amet porttitor enim vehicula. Suspendisse metus lacus, luctus non lectus sed, ornare rhoncus urna. " +
            "Nam porttitor quis "
    private val fontMetrics = Paint.FontMetrics()
    private val bitmap = getAvatar(IMAGE_SIZE.toInt())
    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val bounds = RectF(0f.dp, 0f.dp, 150f.dp, 150f.dp)

    init {
        paint.getFontMetrics(fontMetrics)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, width - IMAGE_SIZE, IMAGE_PADDING, paint)
        var staticLayout = StaticLayout(txt, paint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
//        staticLayout.draw(canvas)
        var start = 0
        var count = 0
        var maxWidth: Float
        var verticalOffset = -fontMetrics.top
        var measuerWidth = floatArrayOf(0f)
        while (start < txt.length) {
            if (verticalOffset + fontMetrics.bottom < IMAGE_PADDING
                    || verticalOffset + fontMetrics.top > IMAGE_PADDING + IMAGE_SIZE) {
                maxWidth = width.toFloat()
            } else {
                maxWidth = width - IMAGE_SIZE
            }
            count = paint.breakText(txt, start, txt.length, true, maxWidth, measuerWidth)
            canvas.drawText(txt, start, start + count, 0f, verticalOffset, paint)
            verticalOffset += paint.fontSpacing
            start += count
        }


    }

    fun getAvatar(width: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.duola, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.duola, options)
    }


}