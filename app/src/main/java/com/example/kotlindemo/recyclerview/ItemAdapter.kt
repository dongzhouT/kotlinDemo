package com.example.kotlindemo.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.layout_item.view.*

/**
 * 用kotlin实现rv的adapter
 */
class ItemAdapter(var dataList: List<String>) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: String) {
            itemView.item_tv.text = data
        }

    }
}