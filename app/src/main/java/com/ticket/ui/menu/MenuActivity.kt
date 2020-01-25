package com.ticket.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ticket.R
import com.ticket.base.BaseActivity
import com.ticket.ui.game.GameActivity
import com.ticket.utils.getUserName
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, MenuActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        tv_username.text = getUserName(this)
        btn_let.setOnClickListener {
            startActivity(GameActivity.newIntent(this))
        }
    }
}
