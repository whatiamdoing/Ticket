package com.ticket.utils.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ticket.utils.setVisible
import kotlinx.android.synthetic.main.item_records.view.*

class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Triple<Int, String, Int>) {
        itemView.tv_number.text = (data.first + 1).toString() + "."
        itemView.tv_username.text = data.second
        itemView.tv_record.text = data.third.toString()
    }
}