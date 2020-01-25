package com.ticket.di.injection.module.component

import com.ticket.di.injection.module.NetworkModule
import com.ticket.ui.game.GameViewModel
import com.ticket.ui.login.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(postListViewModel: LoginViewModel)
    fun inject(postListViewModel: GameViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
