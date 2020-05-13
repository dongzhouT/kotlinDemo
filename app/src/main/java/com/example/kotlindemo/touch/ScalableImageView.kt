package com.example.kotlindemo.touch

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.example.kotlindemo.dp
import com.example.kotlindemo.utils.getAvatar
import kotlin.math.max
import kotlin.math.min

private val IMAGE_SIZE = 300.dp
private const val EXTRA_SCALE_FRACTOR = 1.5f

/**
 * 图片
 * 双击放大缩小
 * 拖动
 * 双指捏撑
 */
class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(getContext(), IMAGE_SIZE.toInt())
    private var originalOffsetX = 0f//图片初始位置
    private var originalOffsetY = 0f//图片初始位置
    private var offsetX = 0f//X方向偏移
    private var offsetY = 0f//Y方向偏移
    private var smallScale = 0f//最小缩放比
    private var bigScale = 1f//最大缩放比

    private var big = false//是否是大图
    private val customGestureListener = CustomGestureListener()
    private val flingRunner = HenFlingRunner()
    private val gestureDetector = GestureDetectorCompat(context, customGestureListener)
    private val henScaleGestureListener = HenScaleGestureListener()
    private val scaleGesture = ScaleGestureDetector(context, henScaleGestureListener)
    private val scroller = OverScroller(context)
    var currentScale = 0f //缩放比
        set(value) {
            field = value
            invalidate()
        }
    private val scaleAnimator =
            ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (width - IMAGE_SIZE) / 2f
        originalOffsetY = (height - IMAGE_SIZE) / 2f
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FRACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FRACTOR
        }
        currentScale = smallScale
        scaleAnimator.setFloatValues(smallScale, bigScale)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGesture.onTouchEvent(event)
        if (!scaleGesture.isInProgress) {
            //如果是正在缩放，不处理双击或拖拽事件
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        var scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    inner class CustomGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            //只有返回true，才能继续接受触摸事件
            return true
        }

        override fun onScroll(downEvent: MotionEvent?, currentEvent: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (big) {
                //只有在放大的时候可以拖动
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffsets()
                invalidate()
            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            if (big) {
                //双击
                offsetX = (e.x - width / 2) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2) * (1 - bigScale / smallScale)
                fixOffsets()
                scaleAnimator.start()
            } else {
                scaleAnimator.reverse()
            }
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (big) {
                //惯性滑动
                //初始位置是offsetX,offsetY,边界（最大位移）是图片放大后的尺寸减去view的尺寸，除以2
                scroller.fling(offsetX.toInt(), offsetY.toInt(),
                        velocityX.toInt(), velocityY.toInt(),
                        (-(bitmap.width * bigScale - width) / 2).toInt(),
                        ((bitmap.width * bigScale - width) / 2).toInt(),
                        (-(bitmap.height * bigScale - height) / 2).toInt(),
                        ((bitmap.height * bigScale - height) / 2).toInt())
                ViewCompat.postOnAnimation(this@ScalableImageView, flingRunner)
            }

            return true
        }

    }

    /**
     * 修正偏移量
     */
    private fun fixOffsets() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }

    inner class HenFlingRunner : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                ViewCompat.postOnAnimation(this@ScalableImageView, this)
            }
        }

    }

    inner class HenScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            offsetX = (scaleGesture.focusX - width / 2) * (1 - bigScale / smallScale)
            offsetY = (scaleGesture.focusY - height / 2) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            var tempScaleFractor = currentScale * scaleGesture.scaleFactor
            if (tempScaleFractor < smallScale || tempScaleFractor > bigScale) {
                return false
            } else {
                currentScale *= scaleGesture.scaleFactor
                big = currentScale > smallScale
            }
            return true
        }

    }
}


