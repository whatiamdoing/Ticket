package com.ticket.di.base

import androidx.lifecycle.ViewModel
import com.ticket.di.injection.module.NetworkModule
import com.ticket.di.injection.module.component.DaggerViewModelInjector
import com.ticket.di.injection.module.component.ViewModelInjector
import com.ticket.di.network.PostListViewModel

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
            is PostListViewModel -> injector.inject(this)
        }
    }
}