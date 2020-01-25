package com.ticket.ui.tutorial.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ticket.R
import com.ticket.ui.game.GameActivity
import com.ticket.ui.tutorial.TutorialActivity
import com.ticket.utils.setGone
import com.ticket.utils.setVisible
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
            btn_exit.setVisible()
        } else {
            btn_exit.setGone()
        }
        btn_exit.setOnClickListener{
            startActivity(GameActivity.newIntent(activity!!))
        }
    }
}
