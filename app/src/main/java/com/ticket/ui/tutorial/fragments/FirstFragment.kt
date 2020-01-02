package com.ticket.ui.tutorial.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ticket.R
import com.ticket.ui.game.GameActivity
import com.ticket.ui.game.fragments.GameFragment
import com.ticket.ui.menu.MenuActivity
import com.ticket.ui.tutorial.TutorialActivity
import kotlinx.android.synthetic.main.fragment_one.*

class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val visibility = (activity!! as TutorialActivity).tutorialQualifier
        if(visibility == false ){
            btn_exit.visibility = View.VISIBLE
        } else {
            btn_exit.visibility = View.GONE
        }
        btn_exit.setOnClickListener{
            startActivity(MenuActivity.newIntent(activity!!))
        }
    }
}
