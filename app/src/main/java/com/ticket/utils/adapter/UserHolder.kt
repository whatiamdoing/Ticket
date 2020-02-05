package com.ticket.utils.adapter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.ticket.R
import com.ticket.entity.UserDTO
import com.ticket.utils.setVisible
import kotlinx.android.synthetic.main.item_records.view.*

class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Pair<Int, UserDTO>) {
        when(data.first){
            0 -> {
                itemView.iv_crown.setVisible()
                itemView.iv_crown.startAnimation(startScale())
                itemView.rl_container.startAnimation(startScale())
                itemView.tv_number.textSize = 25F
                itemView.tv_username.textSize = 25F
                itemView.tv_record.textSize = 25F
            }
        }
        itemView.tv_number.text = String.format(itemView.tv_number.resources.getString(R.string.number), data.first + 1)
        itemView.tv_username.text = data.second.name
        itemView.tv_record.text = data.second.record.toString()
    }
    private fun startScale() = (AnimationUtils.loadAnimation(itemView.context, R.anim.scale))
}