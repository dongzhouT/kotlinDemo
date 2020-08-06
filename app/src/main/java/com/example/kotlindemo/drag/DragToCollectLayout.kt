package com.example.kotlindemo.drag

import android.content.ClipData
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import kotlinx.android.synthetic.main.activity_drag_to_collect.view.*

class DragToCollectLayout(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private val longClickListener = OnLongClickListener { v ->
        var clipdata = ClipData.newPlainText("label", v.contentDescription)
        ViewCompat.startDragAndDrop(v, clipdata, DragShadowBuilder(v), v, 0)
        false
    }
    private val dragListener = OnDragListener { v, event ->
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                if (v is FrameLayout) {
                    Toast.makeText(context, event.clipData.getItemAt(0).text, Toast.LENGTH_LONG).show()
                }
            }
        }
        true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        img1.setOnLongClickListener(longClickListener)
        img2.setOnLongClickListener(longClickListener)
        dragFrameLayout.setOnDragListener(dragListener)
    }
}