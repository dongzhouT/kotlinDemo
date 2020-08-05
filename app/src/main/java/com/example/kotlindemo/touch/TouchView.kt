package com.example.kotlindemo.touch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView

/**
 * onTouchEvent(event: MotionEvent)
 * event.actionMasked return true等同于event.actionMasked==MotionEvent.ACTION_DOWN 返回值只有在down事件中有意义
 */
class TouchView(context: Context?, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
    private val TAG = "==TouchView=="
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked != MotionEvent.ACTION_MOVE) {
//            println(TAG + event.toString())
            println("$TAG+${MotionEvent.actionToString(event.actionMasked)}," +
                    "actionIdex=${event.actionIndex},pointCount=${event.pointerCount}," +
                    "${event.getPointerId(0)}")
        }
//        when (event.actionMasked) {
//            MotionEvent.ACTION_DOWN -> {
//                println("${TAG}:MotionEvent.ACTION_DOWN,${event.actionIndex}/${event.pointerCount}")
//            }
////            MotionEvent.ACTION_MOVE -> println("${TAG}:MotionEvent.ACTION_MOVE")
//            MotionEvent.ACTION_POINTER_DOWN -> {
//                println("${TAG}:MotionEvent.ACTION_POINTER_DOWN,${event.actionIndex}/${event.pointerCount}")
//            }
//            MotionEvent.ACTION_POINTER_UP -> {
//                println("${TAG}:MotionEvent.ACTION_POINTER_UP,${event.actionIndex}/${event.pointerCount}")
//
//            }
//            MotionEvent.ACTION_UP -> {
//                println("${TAG}:MotionEvent.ACTION_UP,${event.actionIndex}/${event.pointerCount}")
//
//            }
//            MotionEvent.ACTION_CANCEL -> {
//                println("${TAG}:MotionEvent.ACTION_CANCEL")
//            }
//        }
        return true
    }
}