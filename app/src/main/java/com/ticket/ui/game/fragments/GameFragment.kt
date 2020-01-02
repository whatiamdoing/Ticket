package com.ticket.ui.game.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ticket.R
import com.ticket.ui.menu.MenuActivity
import com.ticket.ui.tutorial.TutorialActivity
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
        }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {

        btn_back?.setOnClickListener{
            startActivity(MenuActivity.newIntent(activity!!))
        }

        btn_info?.setOnClickListener{
            startActivity(TutorialActivity.newTutorialIntent(activity!!, false))
        }
    }
}

