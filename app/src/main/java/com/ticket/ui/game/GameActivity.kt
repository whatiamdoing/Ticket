package com.ticket.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.ticket.R
import com.ticket.base.BaseActivity
import com.ticket.ui.game.fragments.GameFragment
import com.ticket.utils.Constants.Timer.MILLISECONDS_IN_SECONDS
import com.ticket.utils.Constants.Timer.TIMER_TIME_IN_MILLISECONDS
import kotlinx.android.synthetic.main.fragment_timer.*

class GameActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, GameActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val prepareTimer = object : CountDownTimer(TIMER_TIME_IN_MILLISECONDS, MILLISECONDS_IN_SECONDS){

            override fun onFinish() {
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.fragment_game, GameFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }

            override fun onTick(millisUntilFinished: Long) {
                tv_getReady?.text = String.format(getString(R.string.get_ready), (millisUntilFinished/MILLISECONDS_IN_SECONDS))
            }
        }
        prepareTimer.start()
    }
}


