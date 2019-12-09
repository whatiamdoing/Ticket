package com.ticket.ui.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ticket.entity.User
import com.ticket.base.BaseViewModel
import com.ticket.di.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel: BaseViewModel(){
    @Inject
    lateinit var apiService: ApiService

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()
    val successLiveData: MutableLiveData<String> = MutableLiveData()

    fun sendName(name:String){
        subscription = apiService.createUser(name, User(java.util.UUID.randomUUID().toString(), 0))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { successLiveData.value = it.message() },
                { errorLiveData.value = it.message }
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess() {

    }

    private fun onRetrievePostListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}