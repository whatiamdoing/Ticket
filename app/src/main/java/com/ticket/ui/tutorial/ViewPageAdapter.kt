package com.ticket.ui.tutorial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPageAdapter(
    manager: FragmentManager,
    val fragmentList: List<Fragment>
) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}