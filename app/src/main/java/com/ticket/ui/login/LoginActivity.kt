package com.ticket.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.ticket.R
import com.ticket.base.BaseActivity
import com.ticket.ui.menu.MenuActivity
import com.ticket.utils.Constants
import com.ticket.utils.setUserId
import com.ticket.utils.setUserName
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

        observeSuccessMessage()

        btn_login.setOnClickListener {
            saveNickname()
        }
    }


    private fun observeSuccessMessage() {
        viewModel.successLiveData.observe(this, androidx.lifecycle.Observer{
            Snackbar.make(btn_login,getString(R.string.message_success), Snackbar.LENGTH_LONG).show()
            Handler().postDelayed({
                startActivity(MenuActivity.newIntent(this))
            }, Constants.Delays.TIME_DELAY)
        })
        viewModel.errorLiveData.observe(this, androidx.lifecycle.Observer{
            Snackbar.make(btn_login,getString(R.string.message_error), Snackbar.LENGTH_LONG).show()
        })
    }

    private fun saveNickname(){
        val userId = UUID.randomUUID().toString()
        val name = et_login.text.toString().trim()
        if(name.isEmpty()){
            et_login.error = getString(R.string.error_enter_the_name)
            return
        } else {
            setUserName(this, name)
            setUserId(this, userId)
            viewModel.sendName(name, userId)
        }
    }
}
