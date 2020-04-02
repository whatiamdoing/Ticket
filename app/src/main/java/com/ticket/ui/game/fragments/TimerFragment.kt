package com.ticket.ui.game.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ticket.R
import com.ticket.utils.Constants
import com.ticket.utils.Constants.Timer.MILLISECONDS_IN_SECONDS
import com.ticket.utils.Constants.Timer.TIMER_TIME_IN_MILLISECONDS
import com.ticket.utils.setGone
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val prepareTimer = object : CountDownTimer(TIMER_TIME_IN_MILLISECONDS, MILLISECONDS_IN_SECONDS) {

            override fun onFinish() {
                navController.navigate(R.id.action_timerFragment_to_gameFragment)
            }

            override fun onTick(millisUntilFinished: Long) {
                tv_getReady?.text = String.format(getString(R.string.get_ready), (millisUntilFinished/MILLISECONDS_IN_SECONDS))
            }
        }
        prepareTimer.start()
    }
}
