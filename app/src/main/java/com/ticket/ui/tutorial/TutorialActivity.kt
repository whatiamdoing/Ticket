package com.ticket.ui.tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ticket.R
import com.ticket.ui.login.LoginScreen
import com.ticket.ui.tutorial.fragments.FirstFragment
import com.ticket.ui.tutorial.fragments.SecondFragment
import com.ticket.ui.tutorial.fragments.ThirdFragment
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val adapter = ViewPageAdapter(
            supportFragmentManager,
            listOf(
                FirstFragment(),
                SecondFragment(),
                ThirdFragment()
            )
        )

        viewPager.adapter = adapter
        tabDots.setupWithViewPager(viewPager, true)
    }
}
