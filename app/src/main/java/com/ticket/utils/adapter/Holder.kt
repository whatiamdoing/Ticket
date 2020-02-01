package com.ticket.utils.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_records.view.*

class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Pair<String, Int>) {
        itemView.numbers.text = data.second.toString()
        itemView.username.text = data.first
        Log.d("M_Holder", data.toString())
    }
}