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
import com.ticket.base.BaseActivity
import com.ticket.ui.game.GameViewModel
import com.ticket.ui.menu.MenuActivity
import com.ticket.ui.tutorial.TutorialActivity
import com.ticket.utils.*
import com.ticket.utils.Constants.Delays.GAME_DELAY
import com.ticket.utils.Constants.Denominators.HUNDRED
import com.ticket.utils.Constants.Denominators.HUNDRED_THOUSAND
import com.ticket.utils.Constants.Denominators.TEN
import com.ticket.utils.Constants.Denominators.TEN_THOUSAND
import com.ticket.utils.Constants.Denominators.THOUSAND
import com.ticket.utils.Constants.Timer.MILLISECONDS_IN_SECONDS
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private var points = 0
    private var mistakes = 0
    private lateinit var gameTimer: CountDownTimer

    private var currentTicket: String = getLuckyTicket()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startGame()
        return inflater.inflate(R.layout.fragment_game, container, false)
        }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        setOnClickListeners()
        tv_gameTickets?.text = currentTicket
        tv_points?.text = String.format(getString(R.string.points), points)
        tv_record?.text = String.format((getString(R.string.record)), getUserRecord(activity!!))
        observeSuccessMessage()
        gameViewModel.sendRecord(getUserId(activity!!)!!, getUserRecord(activity!!))
    }

    private fun setOnClickListeners() {
        btn_info?.setOnClickListener{
            startActivity(TutorialActivity.newTutorialIntent(activity!!,
                isForInformation = false,
                isBackVisible = true
            ))
        }
        btn_back?.setOnClickListener{
            builder()
                .setTitle(getString(R.string.exit_to_main_menu_q))
                .setPositiveButton(R.string.yeah) { _: DialogInterface, _: Int ->
                startActivity(MenuActivity.newIntent(activity!!))
                }
                .setNegativeButton(R.string.no) { _: DialogInterface, _: Int -> }
                .show()
        }
        btn_left?.setOnClickListener {
            if(isTicketHappy(currentTicket)){
                onLeftToCorrectTicket()
            } else {
                onRightToCorrectTicket()
            }
        }
        btn_right?.setOnClickListener {
            if(!isTicketHappy(currentTicket)){
                onRightToWrongTicket()
            } else {
                onLeftToWrongTicket()
            }
        }
        btn_tryAgain.setOnClickListener{
            showAlertDialog()
        }
        btn_retry.setOnClickListener{
            showRetryAlert()
        }
    }

    private fun getLuckyTicket(): String {
        val firstNumber = Random.nextInt(0,9)
        val secondNumber = Random.nextInt(0,9)
        val thirdNumber = Random.nextInt(0,9)
        val sum = firstNumber + secondNumber + thirdNumber
        val fourthNumber = Random.nextInt(max(0, sum - 18 + 1), min(sum, 9 + 1))
        val fifthNumber = Random.nextInt(max(0, sum - fourthNumber - 9 + 1), min(sum - fourthNumber, 9 + 1))
        val sixthNumber = sum - fourthNumber - fifthNumber
        return "" + firstNumber + secondNumber + thirdNumber + fourthNumber + fifthNumber + sixthNumber
    }

    private fun getUnLuckyTicket(): String {
        val randomNumber = Random.nextInt(0,9)
        return "" + randomNumber + randomNumber + randomNumber + randomNumber + randomNumber + randomNumber
    }

    private fun isTicketHappy(num: String): Boolean {
        val number = num.toInt()
        val firstHalf = when(num.length){
            6 -> number / HUNDRED_THOUSAND + number / TEN_THOUSAND % TEN + number / THOUSAND % TEN
            5 -> number / TEN_THOUSAND % TEN + number / THOUSAND % TEN
            4 -> number / number / THOUSAND % TEN
            else -> 111111
        }
        val secondHalf = number % TEN + number / TEN % TEN + number / HUNDRED % TEN
        return  firstHalf == secondHalf
    }

    private fun startGame(){
        btn_left?.setVisible()
        btn_right?.setVisible()
        setNewTicket()
        mistakes = 0
        points = 0
        tv_points?.text = String.format(getString(R.string.points),0)
        tv_gameTime?.setVisible()
        btn_tryAgain?.setGone()
        gameTimer = object : CountDownTimer(
            GAME_DELAY,
            MILLISECONDS_IN_SECONDS
        ){
            override fun onFinish() {
                Handler().postDelayed({
                    btn_tryAgain?.setVisible()
                    tv_gameTime?.setGone()
                }, Constants.Delays.TIME_DELAY)
                tv_gameTime?.text = getString(R.string.time_is_over)
                btn_left.setNotClickable()
                btn_right.setNotClickable()
                btn_back.setClickable()
                btn_info.setClickable()
                setNewRecord(points)
                showAlertDialog()
            }
            override fun onTick(millisUntilFinished: Long) {
                tv_gameTime?.text = (millisUntilFinished/MILLISECONDS_IN_SECONDS).toString()
                btn_info?.setNotClickable()
            }
        }
        gameTimer.start()
    }

    private fun showAlertDialog(){
            builder()
                .setMessage(String.format(getString(R.string.yours_result), points, when(points){
                1, 21, 31, 41, 51 -> getString(R.string.point_o)
                2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44-> getString(R.string.point_a)
                5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27, 28, 29, 30, 35, 36, 37, 38, 39, 40, 45, 46, 47, 48, 49, 50 -> "очков"
                else -> getString(R.string.point_ov)
            }))
                .setTitle(getString(R.string.try_one_more_time))
                .setPositiveButton(getString(R.string.yeah)) { _: DialogInterface, _: Int ->
            tv_points?.text = String.format(getString(R.string.points), 0)
            startGame()
            btn_left.setClickable()
            btn_right.setClickable()
                }
                .setNegativeButton(getString(R.string.no)) { _: DialogInterface, _: Int -> }
                .show()
    }
    private fun builder() = AlertDialog.Builder(activity!!)

    private fun setNewRecord(points: Int){
        if(getUserRecord(activity!!) < points){
            setUserRecord(activity!!, points)
            gameViewModel.sendRecord(getUserId(activity!!)!!, getUserRecord(activity!!))
            tv_record?.text = String.format(getString(R.string.record), points)
        }
    }
    private fun showMistakeDialog(){
        points = 0
        gameTimer.cancel()
        tv_gameTime?.setGone()
        btn_tryAgain.setVisible()
        btn_info?.setClickable()
        btn_back?.setClickable()
        builder()
            .setMessage(getString(R.string.start_over))
            .setTitle(getString(R.string.play_fair))
            .setPositiveButton(R.string.yeah) { _: DialogInterface, _: Int ->
                btn_left.setClickable()
                btn_right.setClickable()
                startGame()
            }
            .setNegativeButton(R.string.no) { _: DialogInterface, _: Int ->
                tv_points?.text = String.format(getString(R.string.points), 0)
                btn_left.setNotClickable()
                btn_right.setNotClickable()
                tv_gameTime?.setGone()
                btn_info.setClickable()
                btn_back.setClickable()
            }
            .show()
    }

    private fun showRetryAlert(){
        builder()
            .setMessage(getString(R.string.start_over))
            .setPositiveButton(R.string.yeah) { _: DialogInterface, _: Int ->
                gameTimer.cancel()
                btn_left.setClickable()
                btn_right.setClickable()
                startGame()
            }
            .setNegativeButton(R.string.no) { _: DialogInterface, _: Int ->
                tv_points?.text = String.format(getString(R.string.points), 0)
                btn_info.setClickable()
                btn_back.setClickable()
            }
            .show()
    }

    private fun setNewTicket(){
        currentTicket = if(Random.nextBoolean()) {
            getLuckyTicket()
        } else {
            getUnLuckyTicket()
        }

        tv_gameTickets?.text = currentTicket
    }

    private fun onLeftToCorrectTicket(){
        mistakes = 0
        points++
        tv_points?.text = String.format(getString(R.string.points), points)
        setNewTicket()
    }

    private fun onRightToCorrectTicket(){
        mistakes++
        if(mistakes == 3){
            btn_right.setNotClickable()
            btn_left.setNotClickable()
            showMistakeDialog()
        } else {
            setNewTicket()
        }
    }

    private fun onLeftToWrongTicket() = onRightToCorrectTicket()
    private fun onRightToWrongTicket() = onLeftToCorrectTicket()

    private fun observeSuccessMessage() {
        gameViewModel.errorLiveData.observe(this, androidx.lifecycle.Observer{
            (activity!! as BaseActivity).showMessage(getString(R.string.message_error))
        })
    }
}

