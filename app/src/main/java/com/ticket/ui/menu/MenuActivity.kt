package com.ticket.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.ticket.R
import com.ticket.base.BaseActivity
import com.ticket.ui.game.GameActivity
import com.ticket.ui.login.LoginActivity
import com.ticket.ui.records.RecordsActivity
import com.ticket.ui.tutorial.TutorialActivity
import com.ticket.utils.Constants
import com.ticket.utils.getUserName
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : BaseActivity() {

    private var doubleBackToExitPressedOnce = false

    companion object {
        fun newIntent(context: Context) = Intent(context, MenuActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        tv_username.text = getUserName(this)
        setOnClickListeners()

    }
    private fun setOnClickListeners(){
        btn_let.setOnClickListener {
            startActivity(GameActivity.newIntent(this))
        }
        btn_records.setOnClickListener {
            startActivity(RecordsActivity.newIntent(this))
        }
        btn_name_changer.setOnClickListener {
            startActivity(LoginActivity.newIntent(this))
        }
        btn_tutorial.setOnClickListener {
            startActivity(TutorialActivity.newTutorialIntent(this, isForInformation = false, isBackVisible = false))
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val newIntent = Intent(Intent.ACTION_MAIN)
            newIntent.addCategory(Intent.CATEGORY_HOME)
            newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(newIntent)
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(
            { doubleBackToExitPressedOnce = false },
            Constants.Delays.BACK_PRESS_DELAY
        )
    }
}
