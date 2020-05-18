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
import com.example.kotlindemo.R
import com.example.kotlindemo.dp
import com.example.kotlindemo.utils.getAvatar
import kotlin.math.max
import kotlin.math.min

/**
 * step1:显示两张图,内贴边和外贴边,确定绘图位置originalOffsetX和originalOffsetY
 * step2:双击放大,缩小,gestureDetectorCompat.onDoubleTap()
 * step3:放大缩小增加属性动画,objectAnimation
 * step4:增加手势拖动,onScroll(),修正offsetX和offsetY的边界值
 * step5:修正放大或者缩小后的偏移,offsetX和offsetY和动画完成度关联
 * step6:修正放大后的偏移,实现从点击的地方放大,并限制放大边界
 * step7:fling效果,overScroller
 * 以上完成双击放大，双击恢复,放大后拖动效果,快速拖动后的惯性滑动
 * step8:增加基本的双指捏撑,scaleGestureDetector
 * step9:双指捏撑 从双指按下的中间点开始做动作
 * step10:双指捏撑(scaleGestureDetector)与手势识别(gestureDetector)消费onTouchEvent(),用到了
 * scaleGestureDetector.isInProgress
 */
private val IMAGE_SIZE = 300.dp//图片尺寸
private val EXTRA_SCALE_FRACTOR = 1.5F//bigScale的放大倍数

class ScalableImageView2(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bitmap = getAvatar(context, R.drawable.rengwuxian, IMAGE_SIZE.toInt())
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var offsetX = 0f//相对偏移量
    private var offsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var gestureListener = TaoGestureListener()
    private var gestureDetector = GestureDetectorCompat(context, gestureListener)
    private var scroller = OverScroller(context)
    private var big = false
    private var currentScale = 0f
        //放缩比
        set(value) {
            field = value
            invalidate()
        }
    //step3
    private var scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale",
            smallScale, bigScale)
    //step7
    private val flingRunner = FlingRunner()
    //step8
    private val scaleGestureListener = TaoScaleGestureListener()
    private val scaleGestureDetector = ScaleGestureDetector(context, scaleGestureListener)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (width - bitmap.width) / 2f
        originalOffsetY = (height - bitmap.height) / 2f
        if (width / bitmap.width.toFloat() < height / bitmap.height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FRACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FRACTOR
        }
        currentScale = smallScale
        scaleAnimator.setFloatValues(smallScale, bigScale)
    }

    override fun onDraw(canvas: Canvas) {
        //计算完成度
        var scaleFractor = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFractor, offsetY * scaleFractor)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //step10
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    private fun fixOffsets() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }


    inner class TaoGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            //step2
            if (big) {
                //step6
                offsetX = (e.x - width / 2) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2) * (1 - bigScale / smallScale)
                //限制放大边界
                fixOffsets()
                scaleAnimator.start()
            } else {
                scaleAnimator.reverse()
            }
            return true
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onScroll(downEvent: MotionEvent?, currentEvent: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            //注意：distanceX是上次的x减去这次的x
            //step4
            if (big) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffsets()
                invalidate()
            }
            return false
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            //todo 用不用加上big判断，好像不用加也可以？
            println("==onFling==e1:x=${e1.x},y=${e1.y};e2:x=${e2.x},y=${e2.y};velocityX=${velocityX},velocityY=${velocityY}")
            scroller.fling(offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                    (-(bitmap.width * bigScale - width) / 2f).toInt(),
                    ((bitmap.width * bigScale - width) / 2f).toInt(),
                    (-(bitmap.height * bigScale - height) / 2f).toInt(),
                    ((bitmap.height * bigScale - height) / 2f).toInt())
            ViewCompat.postOnAnimation(this@ScalableImageView2, flingRunner)

            return false
        }

    }

    inner class FlingRunner : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                println("==onFling==:offsetX=${offsetX},offsetY=${offsetY},scale=${currentScale}")
                invalidate()
                ViewCompat.postOnAnimation(this@ScalableImageView2, flingRunner)
            }
        }

    }

    //step8
    inner class TaoScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            //step9
            offsetX = (detector.focusX - width / 2) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {

        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val tempCurrentScale = currentScale * detector.scaleFactor
            if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
                return false
            } else {
                //todo 此处不用加invalidate()方法，是不是调用了setCurrentScale方法?
                currentScale *= detector.scaleFactor
                return true
            }

        }

    }

}