package com.ticket.ui.login

import com.ticket.base.BaseViewModel
import com.ticket.di.network.ApiService
import com.ticket.entity.User
import com.ticket.utils.Constants
import com.ticket.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class LoginViewModel: BaseViewModel() {

    @Inject
    lateinit var apiService: ApiService

    private val userCreateSubscription = CompositeDisposable()
    private val userChangeSubscription = CompositeDisposable()
    val errorLiveData = SingleLiveEvent<Void>()
    val successLiveData = SingleLiveEvent<String>()

    fun sendName(name:String, id: String) {
        userCreateSubscription.add(
            apiService.createUser(id, User(name, 0))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {}
            .doOnTerminate {}
            .subscribe(
                { successLiveData.call() },
                { errorLiveData.call() }
            ))
    }

    fun changeName(newName: String, id: String) {
        val json = JSONObject()
        json.put("name", newName)
        val body = RequestBody.create(Constants.Api.JSON_REQUEST_TYPE.toMediaTypeOrNull(), json.toString())

        userChangeSubscription.add(
            apiService.changeUserName(id, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{}
                .doOnTerminate{}
                .subscribe(
                    {successLiveData.call()},
                    {errorLiveData.call()}
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        userCreateSubscription.dispose()
        userChangeSubscription.dispose()
    }
}