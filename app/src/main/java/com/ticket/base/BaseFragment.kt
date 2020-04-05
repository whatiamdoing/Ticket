package com.ticket.base

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    fun showMessage(message: String) {
        (activity!! as BaseActivity).showMessage(message)
    }
}