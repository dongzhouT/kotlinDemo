package com.example.kotlindemo.customLayout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val childrenBounds = mutableListOf<Rect>()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var maxWidth = 0
        var lineWidthUsed = 0
        var lineMaxHeight = 0
        var specWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        var specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (specWidthMode != MeasureSpec.UNSPECIFIED &&
                    lineWidthUsed + child.measuredWidth > specWidthSize) {
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
                lineWidthUsed = 0
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            }

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            var childBound = childrenBounds[index]
            childBound.left = lineWidthUsed
            childBound.top = heightUsed
            childBound.right = lineWidthUsed + child.measuredWidth
            childBound.bottom = heightUsed + child.measuredHeight
            childrenBounds[index] = childBound
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
            lineWidthUsed += child.measuredWidth
            widthUsed = max(lineWidthUsed, widthUsed)
            maxWidth = max(widthUsed, maxWidth)
        }
        var selfWidth = widthUsed
        var selfHeight = heightUsed + lineMaxHeight
        setMeasuredDimension(selfWidth, selfHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            child.layout(childrenBounds[index].left, childrenBounds[index].top,
                    childrenBounds[index].right, childrenBounds[index].bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}