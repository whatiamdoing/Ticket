package com.ticket.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.ticket.R
import com.ticket.ui.login.LoginActivity
import com.ticket.ui.menu.MenuActivity
import com.ticket.ui.tutorial.TutorialActivity
import com.ticket.utils.Constants.Delays.SPLASH_TIME_DELAY
import com.ticket.utils.getIsFirstLaunch
import com.ticket.utils.getUserName
import com.ticket.utils.setFirstLaunch
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        var intent: Intent
        Handler().postDelayed({
            intent = if(getIsFirstLaunch(this)) {
                setFirstLaunch(this, false)
                Intent(this, TutorialActivity::class.java)
            } else {
                if(getUserName(this) != null) {
                    Intent(this, MenuActivity::class.java)
                } else {
                    Intent(this, LoginActivity::class.java)
                }
            }
            startActivity(intent)
        },SPLASH_TIME_DELAY)
        iv_logo.startAnimation(fadeInAnimation)
    }
}
