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
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ticket.R
import com.ticket.base.BaseActivity
import com.ticket.ui.game.GameViewModel
import com.ticket.utils.*
import com.ticket.utils.Constants.Delays.GAME_DELAY
import com.ticket.utils.Constants.Denominators.HUNDRED
import com.ticket.utils.Constants.Denominators.HUNDRED_THOUSAND
import com.ticket.utils.Constants.Denominators.TEN
import com.ticket.utils.Constants.Denominators.TEN_THOUSAND
import com.ticket.utils.Constants.Denominators.THOUSAND
import com.ticket.utils.Constants.Others.EXTRA_BACK_BUTTON
import com.ticket.utils.Constants.Others.EXTRA_TUTORIAL
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
    private lateinit var navController: NavController
    private lateinit var currentTicket: String
    private var gameDelay: Long = GAME_DELAY

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
        }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        setOnClickListeners()
        setNewTicket()
        tv_points?.text = String.format(getString(R.string.points), points)
        tv_record?.text = String.format((getString(R.string.record)), getUserRecord(activity!!))
        observeUnSuccessMessage()
        gameViewModel.sendRecord(getUserId(activity!!)!!, getUserRecord(activity!!))
    }

    private fun setOnClickListeners() {
        btn_info?.setOnClickListener {
            val bundle = bundleOf(
                EXTRA_TUTORIAL to false,
                EXTRA_BACK_BUTTON to true
            )
            navController.navigate(R.id.action_gameFragment_to_tutorialFragment, bundle)
        }
        btn_back?.setOnClickListener {
            builder()
                .setCancelable(false)
                .setTitle(getString(R.string.exit_to_main_menu_q))
                .setPositiveButton(R.string.yeah) { _: DialogInterface, _: Int ->
                    navController.navigate(R.id.action_gameFragment_to_menuFragment)
                }
                .setNegativeButton(R.string.no) { _: DialogInterface, _: Int -> }
                .show()
        }
        btn_left?.setOnClickListener {
            if(isTicketHappy(currentTicket)) {
                onLeftToCorrectTicket()
            } else {
                onRightToCorrectTicket()
            }
        }
        btn_right?.setOnClickListener {
            if(!isTicketHappy(currentTicket)) {
                onRightToWrongTicket()
            } else {
                onLeftToWrongTicket()
            }
        }
        btn_tryAgain.setOnClickListener {
            showResultDialog()
        }
        btn_retry.setOnClickListener {
            showRetryDialog()
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
        val firstNumber = Random.nextInt(0,9)
        val secondNumber = Random.nextInt(0,9)
        val thirdNumber = Random.nextInt(0,9)
        val fourthNumber = Random.nextInt(0,9)
        val fifthNumber = Random.nextInt(0,9)
        val sixthNumber = Random.nextInt(0,9)
        return "" + firstNumber + secondNumber + thirdNumber + fourthNumber + fifthNumber + sixthNumber
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

    private fun startGame() {
        btn_left?.setVisible()
        btn_right?.setVisible()
        setNewTicket()
        mistakes = 0
        points = 0
        tv_points?.text = String.format(getString(R.string.points),0)
        tv_gameTime?.setVisible()
        btn_tryAgain?.setGone()
        startTimer()
    }

    private fun startTimer() {
        gameTimer = object : CountDownTimer(
            gameDelay,
            MILLISECONDS_IN_SECONDS
        ){
            override fun onFinish() {
                Handler().postDelayed({
                    btn_tryAgain?.setVisible()
                    tv_gameTime?.setGone()
                }, Constants.Delays.TIME_DELAY)
                btn_left?.setNotClickable()
                btn_right?.setNotClickable()
                btn_back?.setClickable()
                btn_info?.setClickable()
                setNewRecord(points)
                showResultDialog()
            }
            override fun onTick(millisUntilFinished: Long) {
                tv_gameTime?.text = (millisUntilFinished/MILLISECONDS_IN_SECONDS).toString()
                btn_info?.setNotClickable()
            }
        }
        gameTimer.start()
    }

    private fun showResultDialog() {
            builder()
                .setCancelable(false)
                .setMessage(String.format(getString(R.string.yours_result), points, when(points){
                1, 21, 31, 41, 51 -> getString(R.string.point_o)
                2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44-> getString(R.string.point_a)
                5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27, 28, 29, 30, 35, 36, 37, 38, 39, 40, 45, 46, 47, 48, 49, 50 -> "очков"
                else -> getString(R.string.point_ov)
            }))
                .setTitle(getString(R.string.try_one_more_time))
                .setPositiveButton(getString(R.string.yeah)) { _: DialogInterface, _: Int ->
                        tv_points?.text = String.format(getString(R.string.points), 0)
                        btn_left.setClickable()
                        btn_right.setClickable()
                        startGame()
                }
                .setNegativeButton(getString(R.string.no)) { _: DialogInterface, _: Int -> }
                .show()
    }
    private fun builder() = AlertDialog.Builder(activity!!)

    private fun setNewRecord(points: Int) {
        if(getUserRecord(activity!!) < points) {
            setUserRecord(activity!!, points)
            gameViewModel.sendRecord(getUserId(activity!!)!!, getUserRecord(activity!!))
            tv_record?.text = String.format(getString(R.string.record), points)
        }
    }
    private fun showMistakeDialog() {
        gameDelay = GAME_DELAY
        points = 0
        gameTimer.cancel()
        tv_gameTime?.setGone()
        btn_tryAgain.setVisible()
        btn_info?.setClickable()
        btn_back?.setClickable()
        builder()
            .setCancelable(false)
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

    private fun showRetryDialog() {
        builder()
            .setCancelable(false)
            .setMessage(getString(R.string.start_over))
            .setPositiveButton(R.string.yeah) { _: DialogInterface, _: Int ->
                gameDelay = GAME_DELAY
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

    private fun setNewTicket() {
        currentTicket = if(Random.nextBoolean()) {
            getLuckyTicket()
        } else {
            getUnLuckyTicket()
        }
        tv_gameTickets?.text = currentTicket
    }

    private fun onLeftToCorrectTicket() {
        mistakes = 0
        points++
        tv_points?.text = String.format(getString(R.string.points), points)
        setNewTicket()
    }

    private fun onRightToCorrectTicket() {
        mistakes++
        if(points > 0)
            points--
        tv_points?.text = String.format(getString(R.string.points), points)
        if(mistakes == 3) {
            btn_right.setNotClickable()
            btn_left.setNotClickable()
            showMistakeDialog()
        } else {
            setNewTicket()
        }
    }

    private fun onLeftToWrongTicket() = onRightToCorrectTicket()
    private fun onRightToWrongTicket() = onLeftToCorrectTicket()

    private fun observeUnSuccessMessage() {
        gameViewModel.errorLiveData.observe(activity!!, androidx.lifecycle.Observer {
            activity?.let {
                (it as BaseActivity).showMessage(getString(R.string.message_error))
            }
        })
    }

    override fun onPause() {
        super.onPause()
        val lastTime = tv_gameTime.text.toString()
        gameDelay = lastTime.toLong() * 1000
        gameTimer.cancel()
    }

    override fun onResume() {
        super.onResume()
        when(GAME_DELAY) {
            30000L -> startGame()
            else -> startTimer()
        }
    }
}

