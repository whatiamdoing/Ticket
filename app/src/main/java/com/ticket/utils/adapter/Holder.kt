package com.ticket.utils.adapter

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.ticket.R
import com.ticket.utils.setVisible
import kotlinx.android.synthetic.main.item_records.view.*

class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Triple<Int, String, Int>) {
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
        itemView.tv_username.text = data.second
        itemView.tv_record.text = data.third.toString()
    }
    private fun startScale() = (AnimationUtils.loadAnimation(itemView.context, R.anim.scale))
}