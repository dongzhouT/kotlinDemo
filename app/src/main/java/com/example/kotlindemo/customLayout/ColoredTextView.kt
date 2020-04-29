package com.example.kotlindemo.customLayout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.example.kotlindemo.dp
import java.util.*

class ColoredTextView(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private val COLORS = intArrayOf(
            Color.parseColor("#CCC834"),
            Color.parseColor("#88CC2B"),
            Color.parseColor("#48CC34"),
            Color.parseColor("#38CC5B"),
            Color.parseColor("#3ACC96"),
            Color.parseColor("#38C6CC"),
            Color.parseColor("#406CCC"),
            Color.parseColor("#A360CC")
    )
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val TEXT_SIZES = intArrayOf(16, 22, 28)
    private val PADDING_X = 16.dp.toInt()
    private val PADDING_Y = 8.dp.toInt()
    private val CORNER_RADIUS = 4.dp
    private val random = Random()

    init {
        setTextColor(Color.WHITE)
        paint.color = COLORS[random.nextInt(COLORS.size)]
        textSize = TEXT_SIZES[random.nextInt(TEXT_SIZES.size)].toFloat()
        setPadding(PADDING_X, PADDING_Y, PADDING_X, PADDING_Y)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(0f, 0f,
                width.toFloat(), height.toFloat(),
                CORNER_RADIUS, CORNER_RADIUS, paint)
        super.onDraw(canvas)
    }
}