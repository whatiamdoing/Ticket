package com.ticket.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.ticket.R
import com.ticket.base.BaseActivity

class GameActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, GameActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        // создать лист билетиков
        // подключить первый фрагмент с таймеров в 3 секунды
        // после 3с заменить на другой фрагмент
        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {

            }
        }
        timer.start()
    }
}
