package com.ticket.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ticket.R

class LoginScreen : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginScreen::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
    }
}
