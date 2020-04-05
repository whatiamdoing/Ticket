package com.ticket.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ticket.R
import com.ticket.ui.tutorial.fragments.FirstFragment
import com.ticket.ui.tutorial.fragments.SecondFragment
import com.ticket.ui.tutorial.fragments.ThirdFragment
import com.ticket.utils.Constants.Others.EXTRA_TUTORIAL
import kotlinx.android.synthetic.main.fragment_tutorial.*

class TutorialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = if (arguments?.getBoolean(EXTRA_TUTORIAL)!!) {
            ViewPageAdapter(
                childFragmentManager,
                listOf(
                    FirstFragment(),
                    SecondFragment(),
                    ThirdFragment()
                )
            )
        } else {
            ViewPageAdapter(
                childFragmentManager,
                listOf(
                    FirstFragment(),
                    SecondFragment()
                )
            )
        }
        viewPager.adapter = adapter
        tabDots.setupWithViewPager(viewPager, true)
    }
}
