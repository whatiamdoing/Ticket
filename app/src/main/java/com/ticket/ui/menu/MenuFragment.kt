package com.ticket.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ticket.R
import com.ticket.ui.login.LoginFragment
import com.ticket.ui.records.RecordsFragment
import com.ticket.ui.tutorial.TutorialFragment
import com.ticket.utils.Constants.Others.EXTRA_TUTORIAL
import com.ticket.utils.getUserName
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_username.text = getUserName(activity!!)
        navController = Navigation.findNavController(view)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        btn_let.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_timerFragment)
        }
        btn_records.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_recordsFragment)
        }
        btn_name_changer.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_loginFragment)
        }
        btn_tutorial.setOnClickListener {
            val bundle = bundleOf(EXTRA_TUTORIAL to false)
            navController.navigate(R.id.action_menuFragment_to_tutorialFragment, bundle)
        }
    }
}
