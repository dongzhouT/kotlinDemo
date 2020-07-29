package com.example.kotlindemo.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

/**
 * 重写onMeasure()计算出自己的尺寸，setMeasuredDimension(width,height)保存结果
 * getMeasuredWidth()和getMeasuredHeight()可以获得测量过程中的期望尺寸width和height
 * getWidth()和getHeight()实际的width和height,测量以外用这两个
 * onLayout() 子view布局，默认没有实现
 * layout(l,t,r,b)也可以改写view尺寸，但是不会将尺寸通知到父View
 *
 * onLayout()用于安排子view的布局
 */
class SquareImageView(context: Context?, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }
    /**
     * 不要重写layout()方法
     */
//    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        val width=r-l
//        val height=b-t
//        val size= min(width,height)
//        super.layout(l, t, l+size, b+size)
//    }
}