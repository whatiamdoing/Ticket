package com.ticket.ui.game.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ticket.R
import com.ticket.ui.game.GameViewModel
import com.ticket.ui.menu.MenuActivity
import com.ticket.ui.tutorial.TutorialActivity
import com.ticket.utils.Constants
import com.ticket.utils.Constants.Delays.GAME_DELAY
import com.ticket.utils.Constants.Timer.MILLISECONDS_IN_SECONDS
import com.ticket.utils.getUserName
import com.ticket.utils.getUserRecord
import com.ticket.utils.setUserRecord
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
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
        gameStarter()
        return inflater.inflate(R.layout.fragment_game, container, false)
        }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
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
            val builder = AlertDialog.Builder(activity!!)
            builder.setTitle(getString(R.string.exit_to_main_menu_q))
            builder.setPositiveButton(R.string.yeah) { dialogInterface: DialogInterface, i: Int ->
                startActivity(MenuActivity.newIntent(activity!!))
            }
            builder.setNegativeButton(R.string.no,{ dialogInterface: DialogInterface, i: Int -> })
            builder.show()
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
        btn_tryAgain.setOnClickListener{
            alertDialog()
        }
    }

    private fun ticketDefinition(num: Int): Boolean {
        val firstHalf: Int = num / 100000 + num / 10000 % 10 + num / 1000 % 10
        val secondHalf: Int = num % 10 + num / 10 % 10 + num / 100 % 10
        return  firstHalf == secondHalf
    }

    private fun gameStarter(){
        points = 0
        tv_points?.text = String.format(getString(R.string.points),0)
        tv_gameTime?.visibility = View.VISIBLE
        btn_tryAgain?.visibility = View.GONE
        val gameTimer = object : CountDownTimer(
            GAME_DELAY,
            MILLISECONDS_IN_SECONDS
        ){
            override fun onFinish() {
                Handler().postDelayed({
                    btn_tryAgain?.visibility = View.VISIBLE
                    tv_gameTime?.visibility = View.GONE
                }, Constants.Delays.TIME_DELAY)
                tv_gameTime?.text = getString(R.string.time_is_over)
                btn_left.isClickable = false
                btn_right.isClickable = false
                btn_back.isClickable = true
                btn_info.isClickable = true
                if(getUserRecord(activity!!) < points){
                    setUserRecord(activity!!, points)
                    tv_record?.text = String.format(getString(R.string.record), points)
                    gameViewModel.sendRecord(getUserName(activity!!)!!, getUserRecord(activity!!))
                }
                alertDialog()
            }
            override fun onTick(millisUntilFinished: Long) {
                tv_gameTime?.text = (millisUntilFinished/MILLISECONDS_IN_SECONDS).toString()
                btn_back?.isClickable = false
                btn_info?.isClickable = false
            }
        }
        gameTimer.start()
    }

    private fun alertDialog(){
        val builder = AlertDialog.Builder(activity!!)
            builder.setMessage(String.format(getString(R.string.yours_result), points, when(points){
                1, 21, 31, 41, 51 -> getString(R.string.point_o)
                2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44-> getString(R.string.point_a)
                5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27, 28, 29, 30, 35, 36, 37, 38, 39, 40, 45, 46, 47, 48, 49, 50 -> "очков"
                else -> getString(R.string.point_ov)
            }))
            builder.setTitle(getString(R.string.try_one_more_time))
            builder.setPositiveButton(getString(R.string.yeah)) { dialogInterface: DialogInterface, i: Int ->
            tv_points?.text = String.format(getString(R.string.points), 0)
            gameStarter()
            btn_left.isClickable = true
            btn_right.isClickable = true
        }
        builder.setNegativeButton(getString(R.string.no)) { dialogInterface: DialogInterface, i: Int -> }
        builder.show()
    }
}

