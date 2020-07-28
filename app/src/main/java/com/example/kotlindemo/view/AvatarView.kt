package com.example.kotlindemo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.R
import com.example.kotlindemo.dp

private val IMAGE_WIDTH = 200f.dp
private val IMAGE_PADDING = 20f.dp

class AvatarView(context: Context?, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING + IMAGE_WIDTH,
            IMAGE_PADDING + IMAGE_WIDTH)
    val xfermode=PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    override fun onDraw(canvas: Canvas) {
        setLayerType(LAYER_TYPE_HARDWARE,null)
        super.onDraw(canvas)
        val count = canvas.saveLayer(bounds, null)
        canvas.drawOval(bounds, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(getBitmap(IMAGE_WIDTH.toInt()), IMAGE_PADDING, IMAGE_PADDING, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)
    }

    fun getBitmap(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.duola, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.duola, options)
    }
}