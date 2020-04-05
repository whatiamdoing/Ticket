package com.ticket.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ticket.R
import com.ticket.utils.Constants.Delays.SPLASH_TIME_DELAY
import com.ticket.utils.Constants.Others.EXTRA_TUTORIAL
import com.ticket.utils.getIsFirstLaunch
import com.ticket.utils.getUserName
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class SplashFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        Handler().postDelayed({
            if(getIsFirstLaunch(activity!!)) {
                navController.navigate(R.id.action_splashFragment_to_tutorialFragment, bundleOf(EXTRA_TUTORIAL to true))
            } else {
                if(getUserName(activity!!) != null) {
                    navController.navigate(R.id.action_splashFragment_to_menuFragment)
                } else {
                    navController.navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        },SPLASH_TIME_DELAY)
        iv_logo.startAnimation(AnimationUtils.loadAnimation(activity!!, R.anim.fade_in))
    }
}

