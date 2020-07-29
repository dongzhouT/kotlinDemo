package com.example.kotlindemo.drawable

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.kotlindemo.R
import com.example.kotlindemo.dp

private val TEXT_SIZE = 12.dp
private val TEXT_MARGIN = 8.dp
private val OFFSET = 23.dp

class MaterialEdittext2(context: Context?, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    var verticalOffset = 18.dp
    var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 12.dp
    }
    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "fraction", 0f, 1f)
    }
    var fraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    var animShown = false
    var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(paddingLeft, (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(), paddingRight, paddingBottom)
                } else {
                    setPadding(paddingLeft, (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(), paddingRight, paddingBottom)
                }
            }
        }

    init {
        var typeArray = resources.obtainAttributes(attrs, R.styleable.MaterialEdittext2)
        useFloatingLabel = typeArray.getBoolean(R.styleable.MaterialEdittext2_useFloatingLabel2, true)
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (text.isNullOrEmpty() && animShown) {
            animator.reverse()
            animShown = false
        } else if (!animShown && !text.isNullOrEmpty()) {
            animator.start()
            animShown = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (useFloatingLabel) {
            paint.alpha = (fraction * 0xff).toInt()
            var currentOffset = verticalOffset + OFFSET * (1 - fraction)
            canvas.drawText(hint.toString(), paddingLeft.toFloat(), currentOffset, paint)
        }


    }


}