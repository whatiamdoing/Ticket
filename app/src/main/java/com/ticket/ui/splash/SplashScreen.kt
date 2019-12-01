package com.ticket.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.ticket.R
import com.ticket.ui.tutorial.TutorialActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        Handler().postDelayed({
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
        },3000)

        splash_iv.startAnimation(fadeInAnimation)
    }
}
