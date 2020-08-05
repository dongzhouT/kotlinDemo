package com.example.kotlindemo.multiTouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import androidx.core.util.Pools
import androidx.core.util.keyIterator
import com.example.kotlindemo.dp
import com.example.kotlindemo.utils.getAvatar

/**
 * 互不干扰型多点触控 画板程序
 */
class MultiTouchImageView3(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    var tag = "==MultiTouchImageView3=="
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {

    }
    var paths = SparseArray<Path>()
    var pathPool = Pools.SimplePool<Path>(5)

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3.dp
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        for (i in 0 until paths.size()) {
            val path = paths.valueAt(i)
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                var path = pathPool.acquire() ?: Path()
                println("$tag new path=${path.toString()}")
                path.reset()
//                var path = Path()
                val actionIndex = event.actionIndex
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
                paths.append(event.getPointerId(actionIndex), path)
                invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                println("${tag},${event.actionMasked},event.actionIdex=${event.actionIndex},pointerId=${pointerId}")
                pathPool.release(paths.get(pointerId))
                paths.remove(pointerId)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until paths.size()) {
                    val pointerId = event.getPointerId(i)
                    val path = paths.get(pointerId)
                    path.lineTo(event.getX(i), event.getY(i))
                }
                invalidate()
            }
//            MotionEvent.ACTION_UP -> {
//                var actionIndex = event.actionIndex
//                var pointerId = event.getPointerId(actionIndex)
//                println("${tag},ACTION_UP,event.actionIdex=${event.actionIndex},pointerId=${pointerId}")
//                paths.remove(pointerId)
//                invalidate()
//            }
        }
        return true
    }
}