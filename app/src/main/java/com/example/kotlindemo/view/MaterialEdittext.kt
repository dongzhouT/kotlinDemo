package com.example.kotlindemo.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.kotlindemo.R
import com.example.kotlindemo.dp

private val HORIZONTAL_OFFSET = 5.dp
private val VERTICAL_OFFSET = 23.dp
private val TEXT_MARGIN = 10.dp
private val TEXT_SIZE = 12.dp
private val EXTRA_VERTICAL_OFFSET = 16.dp


class MaterialEdittext(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var floatingLabelShown = false
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
    }
    var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(paddingLeft, (paddingTop + TEXT_MARGIN + TEXT_SIZE).toInt(), paddingRight, paddingBottom)
                } else {
                    setPadding(paddingLeft, (paddingTop - TEXT_MARGIN - TEXT_SIZE).toInt(), paddingRight, paddingBottom)
                }
            }
        }
    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 1f)
    }

    init {
        /**
         * int[] styleable MaterialEdittext { 0x7f020143 }
         * int styleable MaterialEdittext_userFloatingLabel 0
         * int attr useFloatingLabel 0x7f020143
         *
         *
         *
         */
        for (index in 0 until attrs.attributeCount) {
            println("attrs==>key=${attrs.getAttributeName(index)},value=${attrs.getAttributeValue(index)}")
        }
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEdittext)
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEdittext_useFloatingLabel, true)
        /*
        本质是以下代码
        val typedArray = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatingLabel))
        useFloatingLabel = typedArray.getBoolean(0, true)
        */
        typedArray.recycle()
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (floatingLabelShown && text.isNullOrEmpty()) {
            floatingLabelShown = false
            animator.reverse()
        } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
            floatingLabelShown = true
            animator.start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (useFloatingLabel) {
            paint.alpha = (0xff * floatingLabelFraction).toInt()
            var currentVerticalValue = VERTICAL_OFFSET + (1 - floatingLabelFraction) * EXTRA_VERTICAL_OFFSET
            canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paint)
        }
    }
}