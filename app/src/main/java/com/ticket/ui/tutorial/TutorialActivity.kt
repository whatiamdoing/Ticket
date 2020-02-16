package com.ticket.ui.tutorial

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ticket.R
import com.ticket.ui.tutorial.fragments.FirstFragment
import com.ticket.ui.tutorial.fragments.SecondFragment
import com.ticket.ui.tutorial.fragments.ThirdFragment
import com.ticket.utils.Constants.Others.EXTRA_BACK
import com.ticket.utils.Constants.Others.EXTRA_NAME
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {
     var tutorialQualifier: Boolean? = null
    var backQualifier: Boolean? = null

    companion object {
       fun newTutorialIntent(context: Context, isForInformation: Boolean, isBackVisible: Boolean): Intent{
            val intent = Intent(context, TutorialActivity::class.java)
            intent.putExtra(EXTRA_NAME, isForInformation)
            intent.putExtra(EXTRA_BACK, isBackVisible)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        backQualifier = intent.getBooleanExtra(EXTRA_BACK, true)
        tutorialQualifier = intent.getBooleanExtra(EXTRA_NAME, true)
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
