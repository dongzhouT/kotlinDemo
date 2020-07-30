package com.example.kotlindemo.customLayout

import android.content.Context
import android.graphics.Rect
import android.os.AsyncTask
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout2(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    val childrenBounds = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var lineWidthUsed = 0
        var heightUsed = 0
        var widthUsed = 0
        var lineMaxHeight = 0
        var widthSpceMode = MeasureSpec.getMode(widthMeasureSpec)
        var widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (widthSpceMode != MeasureSpec.UNSPECIFIED
                    && lineWidthUsed + child.measuredWidth > widthSpecSize) {
                lineWidthUsed = 0
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            }
            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            var childBound = childrenBounds[index]
            childBound.set(lineWidthUsed, heightUsed,
                    lineWidthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
            lineWidthUsed += child.measuredWidth
            widthUsed = max(widthUsed, lineWidthUsed)
        }
        var width = widthUsed
        var height = heightUsed + lineMaxHeight
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            var childBound = childrenBounds[index]
            child.layout(childBound.left, childBound.top, childBound.right, childBound.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }


}