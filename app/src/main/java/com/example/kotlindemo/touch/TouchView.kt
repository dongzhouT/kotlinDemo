package com.example.kotlindemo.touch

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class TouchView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(event.actionMasked==MotionEvent.ACTION_POINTER_UP){

        }
        return super.onTouchEvent(event)
    }
}