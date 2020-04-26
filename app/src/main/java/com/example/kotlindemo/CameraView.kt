package com.example.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.blankj.utilcode.util.GsonUtils

private val IMAGE_SIZE = 150.dp
private val IMAGE_PADDING = 100.dp


class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(IMAGE_SIZE.toInt())
    private val camera = Camera()
    private var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }


    init {
        camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {

        println("canvas==>" + GsonUtils.toJson(resources.displayMetrics.density))
        //上半部分
        canvas.withSave {
            canvas.translate(IMAGE_PADDING + IMAGE_SIZE / 2, IMAGE_PADDING + IMAGE_SIZE / 2)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(-IMAGE_SIZE, -IMAGE_SIZE, IMAGE_SIZE, 0f)
            canvas.rotate(flipRotation)
            canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE / 2), -(IMAGE_PADDING + IMAGE_SIZE / 2))
            canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint)
        }
        //下半部分
        canvas.withSave {
            canvas.translate(IMAGE_PADDING + IMAGE_SIZE / 2, IMAGE_PADDING + IMAGE_SIZE / 2)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(-IMAGE_SIZE, 0f, IMAGE_SIZE, IMAGE_SIZE)
            canvas.rotate(flipRotation)
            canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE / 2), -(IMAGE_PADDING + IMAGE_SIZE / 2))
            canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint)
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