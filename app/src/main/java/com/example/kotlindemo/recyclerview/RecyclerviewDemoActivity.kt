package com.example.kotlindemo.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_recyclerview_demo.*

class RecyclerviewDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_demo)
        rv.layoutManager = LinearLayoutManager(this)
        val dataList = listOf<String>("aa", "bb", "cc", "dd", "ee")
        var adapter = ItemAdapter(dataList)
        rv.adapter = adapter
    }
}
