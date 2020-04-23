package com.example.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.GsonUtils
import kotlin.math.log

private val IMAGE_SIZE = 150.dp
private val IMAGE_PADDING = 100.dp


class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(IMAGE_SIZE.toInt())
    private val camera = Camera()
    private var ROTATE_ANGLE = 30f
    private var CAMERA_ANGLE = 30f
        set(value) {
            field = value
            invalidate()
        }

    init {
        camera.setLocation(0f, 0f, -3 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        camera.save()
        println("canvas==>" + GsonUtils.toJson(resources.displayMetrics.density))
        //上半部分
        canvas.save()
        canvas.translate(IMAGE_PADDING + IMAGE_SIZE / 2, IMAGE_PADDING + IMAGE_SIZE / 2)
        canvas.rotate(-ROTATE_ANGLE)
        canvas.clipRect(-IMAGE_SIZE, -IMAGE_SIZE, IMAGE_SIZE, 0f)
        canvas.rotate(ROTATE_ANGLE)
        canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE / 2), -(IMAGE_PADDING + IMAGE_SIZE / 2))
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint)
        canvas.restore()
        //下半部分
        canvas.save()
        canvas.translate(IMAGE_PADDING + IMAGE_SIZE / 2, IMAGE_PADDING + IMAGE_SIZE / 2)
        canvas.rotate(-ROTATE_ANGLE)

        camera.rotateX(CAMERA_ANGLE)
        camera.applyToCanvas(canvas)

        canvas.clipRect(-IMAGE_SIZE, 0f, IMAGE_SIZE, IMAGE_SIZE)
        canvas.rotate(ROTATE_ANGLE)
        canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE / 2), -(IMAGE_PADDING + IMAGE_SIZE / 2))
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint)
        canvas.restore()
        camera.restore()
    }

    fun setRotate(r: Float) {
        CAMERA_ANGLE = r
        invalidate()
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