package com.example.kotlindemo.drag

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.collection.ArrayMap
import com.example.kotlindemo.R

class DragDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_demo)
        var map = ArrayMap<String, String>()
        var sparseArray = SparseArray<String>()
    }

    fun onClickDragListener(view: View) {
        startActivity(Intent(this, DragListenerActivity::class.java))
    }

    fun onClickDragHelper(view: View) {
        startActivity(Intent(this, DragHelperActivity::class.java))
    }

    fun onClickDragToCollect(view: View) {
        startActivity(Intent(this, DragToCollectActivity::class.java))
    }

    fun onClickDragUpDown(view: View) {
        startActivity(Intent(this, DragUpDownActivity::class.java))
    }
}
