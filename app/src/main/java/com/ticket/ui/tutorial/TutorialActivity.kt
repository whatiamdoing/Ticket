package com.ticket.ui.tutorial

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ticket.R
import com.ticket.ui.tutorial.fragments.FirstFragment
import com.ticket.ui.tutorial.fragments.SecondFragment
import com.ticket.ui.tutorial.fragments.ThirdFragment
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {
     var tutorialQualifier: Boolean? = null

    companion object {

       fun newTutorialIntent(context: Context, isForInformation: Boolean): Intent{
            val intent = Intent(context, TutorialActivity::class.java)
            intent.putExtra("extra-name", isForInformation)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        tutorialQualifier = intent.getBooleanExtra("extra-name", true)
        val adapter = if(tutorialQualifier!!){
            ViewPageAdapter(
                supportFragmentManager,
                listOf(
                    FirstFragment(),
                    SecondFragment(),
                    ThirdFragment()
                )
            )
        } else {
            ViewPageAdapter(
                supportFragmentManager,
                listOf(
                    FirstFragment(),
                    SecondFragment()
                )
            )
        }

        viewPager.adapter = adapter
        tabDots.setupWithViewPager(viewPager, true)
    }
}
