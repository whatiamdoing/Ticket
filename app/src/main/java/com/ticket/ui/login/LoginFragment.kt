package com.ticket.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ticket.R
import com.ticket.base.BaseFragment
import com.ticket.utils.*
import kotlinx.android.synthetic.main.fragment_login_screen.*
import java.util.*

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(LoginViewModel::class.java)
        navController = Navigation.findNavController(view)
        setOnClickListeners()
        setUpButtons()
        observeSuccessMessage()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        btn_login.setOnClickListener {
            saveNickname()
            setFirstLaunch(activity!!, false)
        }
        btn_change.setOnClickListener {
            changeNickname()
        }
        btn_back.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_menuFragment)
        }
    }

    private fun setUpButtons() {
        if(getUserName(activity!!) == null) {
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
        viewModel.successLiveData.observe(activity!!, androidx.lifecycle.Observer {
            navController.navigate(R.id.action_loginFragment_to_menuFragment)
        })
        viewModel.errorLiveData.observe(activity!!, androidx.lifecycle.Observer {
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
            setUserName(activity!!, name)
            setUserId(activity!!, userId)
            viewModel.sendName(name, userId)
        }
    }

    private  fun changeNickname() {
        val name = et_login.text.toString().trim()
        if(name.isEmpty()) {
            et_login.error = getString(R.string.error_enter_the_name)
            return
        } else {
            setUserName(activity!!, name)
            viewModel.changeName(name, getUserId(activity!!)!!)
        }
    }
}
