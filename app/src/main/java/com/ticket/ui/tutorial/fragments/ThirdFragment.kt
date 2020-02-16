package com.ticket.ui.tutorial.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ticket.R
import com.ticket.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_three.*

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_start.setOnClickListener {
            startActivity(LoginActivity.newIntent(activity!!))
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
