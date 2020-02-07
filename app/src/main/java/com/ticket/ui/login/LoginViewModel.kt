package com.ticket.ui.login

import androidx.lifecycle.MutableLiveData
import com.ticket.base.BaseViewModel
import com.ticket.di.network.ApiService
import com.ticket.entity.User
import com.ticket.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoginViewModel: BaseViewModel(){
    @Inject
    lateinit var apiService: ApiService

    private val subscriptions = CompositeDisposable()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData = SingleLiveEvent<Void>()
    val successLiveData = SingleLiveEvent<String>()

    fun sendName(
        name:String, id: String){
        subscriptions.add(apiService.createUser(id, User(name, 0))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doOnTerminate { isLoading.value = false }
            .subscribe(
                { successLiveData.call() },
                { errorLiveData.call() }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}