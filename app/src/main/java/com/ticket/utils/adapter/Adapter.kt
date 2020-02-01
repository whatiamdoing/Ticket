package com.ticket.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ticket.R
import com.ticket.entity.UserDTO

class Adapter(var list: List<UserDTO>) : RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_records, parent, false)
        return Holder(itemView = itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(Triple(position, list[position].name, list[position].record))

    fun updateList(list: List<UserDTO>) {
        this.list = list
        notifyDataSetChanged()
    }
}