package com.ticket.base

import androidx.lifecycle.ViewModel
import com.ticket.di.injection.module.NetworkModule
import com.ticket.di.injection.module.component.DaggerViewModelInjector
import com.ticket.di.injection.module.component.ViewModelInjector
import com.ticket.ui.login.LoginViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is LoginViewModel -> injector.inject(this)
        }
    }
}