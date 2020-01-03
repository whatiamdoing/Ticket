package com.ticket.ui.game.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ticket.R
import com.ticket.ui.menu.MenuActivity
import com.ticket.ui.tutorial.TutorialActivity
import com.ticket.utils.Constants.Delays.GAME_DELAY
import com.ticket.utils.Constants.Timer.MILLISECONDS_IN_SECONDS
import com.ticket.utils.getUserRecord
import com.ticket.utils.setUserRecord
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    private var points = 0

    private val tickets =
        arrayOf(114150, 425920, 461812, 479749, 499679, 207513, 401221, 777777, 111111, 222222, 112320, 105320, 543363,
        333333, 444444, 555555, 666666, 888888, 999999, 367583, 195861, 861159, 673853, 570057, 409607, 229913,
        524029, 146128, 994769, 104212, 425209, 345903, 123303, 123501, 312015, 543291, 194329, 907295, 333603,
        123456, 654321, 333467, 567789, 778932, 223589, 737924, 423772, 234688, 782193, 727812, 516923, 352503,
        234674, 672863, 678216, 261893, 216782, 399337, 680697, 638608, 289389, 711339, 444062, 968615, 405046,
        132502, 101843, 798424, 780534, 386337, 894341, 468595, 480924, 700738, 145958, 168981, 408438, 212965)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val gameTimer = object : CountDownTimer(
            GAME_DELAY,
            MILLISECONDS_IN_SECONDS
        ){
            override fun onFinish() {
                tv_gameTime?.text = "Время вышло"
                btn_left.isClickable = false
                btn_right.isClickable = false
                btn_back.isClickable = true
                btn_info.isClickable = true
                if(getUserRecord(activity!!) < points){
                    setUserRecord(activity!!, points)
                    tv_record?.text = String.format(getString(R.string.record), points)
                }
            }
            override fun onTick(millisUntilFinished: Long) {
                tv_gameTime?.text = (millisUntilFinished/MILLISECONDS_IN_SECONDS).toString()
                btn_back?.isClickable = false
                btn_info?.isClickable = false
            }
        }
        gameTimer.start()

        return inflater.inflate(R.layout.fragment_game, container, false)
        }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {

        setOnClickListeners()
        tv_gameTickets?.text = tickets.random().toString()
        tv_points?.text = String.format(getString(R.string.points), points)
        tv_record?.text = String.format((getString(R.string.record)), getUserRecord(activity!!))
    }

    private fun setOnClickListeners() {
        btn_info?.setOnClickListener{
            startActivity(TutorialActivity.newTutorialIntent(activity!!, false))
        }
        btn_back?.setOnClickListener{
            startActivity(MenuActivity.newIntent(activity!!))
        }
        btn_left?.setOnClickListener {
            if(ticketDefinition(tv_gameTickets.text.toString().toInt())){
                points++
                tv_points?.text = String.format(getString(R.string.points), points)
            }
            tv_gameTickets?.text = tickets.random().toString()
        }
        btn_right?.setOnClickListener {
            if(!ticketDefinition(tv_gameTickets.text.toString().toInt())){
                points++
                tv_points?.text = String.format(getString(R.string.points), points)
            }
            tv_gameTickets?.text = tickets.random().toString()
        }
    }

    private fun ticketDefinition(num: Int): Boolean {
        val firstHalf: Int = num / 100000 + num / 10000 % 10 + num / 1000 % 10
        val secondHalf: Int = num % 10 + num / 10 % 10 + num / 100 % 10
        return  firstHalf == secondHalf
    }
}

