package com.ticket.ui.tutorial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ticket.R
import com.ticket.ui.tutorial.TutorialFragment
import com.ticket.utils.setGone
import com.ticket.utils.setVisible
import kotlinx.android.synthetic.main.fragment_two.*

class SecondFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
//        setVisibility()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        btn_exit.setOnClickListener {
            activity!!.onBackPressed()
        }
        btn_back.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

//    private fun setVisibility() {
//        val isVisible = (activity!! as TutorialFragment).tutorialQualifier
//        val isBackVisible = (activity!! as TutorialFragment).backQualifier
//        if(isVisible == false ) {
//            if (isBackVisible!!) {
//                btn_exit.setVisible()
//            } else {
//                btn_back?.setVisible()
//            }
//        } else {
//            btn_back?.setGone()
//            btn_exit?.setGone()
//        }
//    }
}
