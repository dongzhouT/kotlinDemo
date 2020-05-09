package com.example.kotlindemo.animator

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.dp

class ColorAnimView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var color = Color.RED
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        paint.color = color
        canvas.drawCircle(width / 2f, height / 2f, 100.dp, paint)
    }
}

class HSVEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        var startHsv = FloatArray(3)
        var endHsv = FloatArray(3)
        var curHsv = FloatArray(3)
        Color.colorToHSV(startValue, startHsv)
        Color.colorToHSV(endValue, endHsv)
        curHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
        curHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
        curHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;
        return Color.HSVToColor(curHsv)
    }

}