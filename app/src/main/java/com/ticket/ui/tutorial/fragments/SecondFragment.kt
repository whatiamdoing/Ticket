package com.ticket.ui.tutorial.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ticket.R
import com.ticket.ui.game.GameActivity
import com.ticket.ui.menu.MenuActivity
import com.ticket.ui.tutorial.TutorialActivity
import com.ticket.utils.setGone
import com.ticket.utils.setVisible
import kotlinx.android.synthetic.main.fragment_one.*

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setVisibility()
        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        btn_exit.setOnClickListener{
            startActivity(GameActivity.newIntent(activity!!))
        }
        btn_back.setOnClickListener {
            startActivity(MenuActivity.newIntent(activity!!))
        }
    }

    private fun setVisibility(){
        val isVisible = (activity!! as TutorialActivity).tutorialQualifier
        val isBackVisible = (activity!! as TutorialActivity).backQualifier
        if(isVisible == false ){
            if (isBackVisible!!){
                btn_exit.setVisible()
            } else {
                btn_back?.setVisible()
            }
        } else {
            btn_back?.setGone()
            btn_exit?.setGone()
        }
    }
}
