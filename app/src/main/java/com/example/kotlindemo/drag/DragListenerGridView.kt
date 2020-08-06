package com.example.kotlindemo.drag

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

class DragListenerGridView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    val COLUMNS = 2
    val ROWS = 3
    private var targetView: View? = null
    val onLongListener = OnLongClickListener { v ->
        targetView = v
        v.startDrag(null, DragShadowBuilder(v), v, 0)
        false
    }
    private var orderedChildren = mutableListOf<View>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var specWidth = MeasureSpec.getSize(widthMeasureSpec)
        var specHeight = MeasureSpec.getSize(heightMeasureSpec)
        var childWidth = specWidth / COLUMNS
        var childHeight = specHeight / ROWS
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY))
        setMeasuredDimension(specWidth, specHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft: Int
        var childTop: Int
        var childWidth: Int = width / COLUMNS
        var childHeight: Int = height / ROWS
        for ((index, child) in children.withIndex()) {
            orderedChildren.add(child)
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.layout(0, 0, childWidth, childHeight)
            child.translationX = childLeft.toFloat()
            child.translationY = childTop.toFloat()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        for ((index, child) in children.withIndex()) {
            child.setOnLongClickListener(onLongListener)
            child.setOnDragListener(MyDragListener())
        }
    }

    private inner class MyDragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    if (v === targetView) {
                        v.visibility = View.INVISIBLE
                    }
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    if (v !== targetView)
                        sort(v)
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    if (v === targetView) {
                        v.visibility = visibility
                    }
                }
            }
            return true

        }
    }

    private fun sort(v: View) {
        var targetIndex = -1
        var thisIndex = -1
        for ((index, child) in orderedChildren.withIndex()) {
            if (child === targetView) {
                targetIndex = index
            } else if (v === child) {
                thisIndex = index
            }
        }
        println("sortIndex=$targetIndex,$thisIndex")
        orderedChildren.removeAt(targetIndex)
        orderedChildren.add(thisIndex, targetView!!)
        var childLeft: Int
        var childTop: Int
        var childWidth: Int = width / COLUMNS
        var childHeight: Int = height / ROWS
        for ((index, child) in orderedChildren.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.animate().translationX(childLeft.toFloat())
                    .translationY(childTop.toFloat())
        }
    }
}