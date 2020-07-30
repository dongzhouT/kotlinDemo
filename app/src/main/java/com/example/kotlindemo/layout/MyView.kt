package com.example.kotlindemo.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View

class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        println("====onMeasure====${MeasureSpec.getSize(widthMeasureSpec)}")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}