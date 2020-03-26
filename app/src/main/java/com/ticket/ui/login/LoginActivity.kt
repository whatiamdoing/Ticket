package com.ticket.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ticket.R
import com.ticket.base.BaseActivity
import com.ticket.ui.menu.MenuActivity
import com.ticket.utils.*
import kotlinx.android.synthetic.main.activity_login_screen.*
import java.util.*

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        setUpButtons()
        observeSuccessMessage()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        btn_login.setOnClickListener {
            saveNickname()
            setFirstLaunch(this, false)
        }
        btn_change.setOnClickListener {
            changeNickname()
        }
        btn_back.setOnClickListener {
            startActivity(MenuActivity.newIntent(this))
        }
    }

    private fun setUpButtons() {
        if(getUserName(this) == null) {
            btn_change?.setGone()
            btn_login?.setVisible()
            btn_back?.setGone()

        } else {
            btn_change?.setVisible()
            btn_login?.setGone()
            btn_back?.setVisible()
        }
    }

    private fun observeSuccessMessage() {
        viewModel.successLiveData.observe(this, androidx.lifecycle.Observer {
                startActivity(MenuActivity.newIntent(this))
        })
        viewModel.errorLiveData.observe(this, androidx.lifecycle.Observer {
            showMessage(getString(R.string.message_error))
        })
    }

    private fun saveNickname() {
        val userId = UUID.randomUUID().toString()
        val name = et_login.text.toString().trim()
        if(name.isEmpty()) {
            et_login.error = getString(R.string.error_enter_the_name)
            return
        } else {
            setUserName(this, name)
            setUserId(this, userId)
            viewModel.sendName(name, userId)
        }
    }

    private  fun changeNickname() {
        val name = et_login.text.toString().trim()
        if(name.isEmpty()) {
            et_login.error = getString(R.string.error_enter_the_name)
            return
        } else {
            setUserName(this, name)
            viewModel.changeName(name, getUserId(this)!!)
        }
    }
}
