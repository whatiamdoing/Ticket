package com.ticket.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ticket.R
import com.ticket.entity.UserDTO

class Adapter(var list: List<UserDTO>) : RecyclerView.Adapter<UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_records, parent, false)
        return UserHolder(itemView = itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) = holder.bind(Pair(position, UserDTO(list[position].id, list[position].name, list[position].record)))

    fun updateList(list: List<UserDTO>) {
        this.list = list
        notifyDataSetChanged()
    }
}