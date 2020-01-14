package com.ticket.ui.game

import androidx.lifecycle.MutableLiveData
import com.ticket.base.BaseViewModel
import com.ticket.di.network.ApiService
import com.ticket.utils.Constants.Api.JSON_REQUEST_TYPE
import com.ticket.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class GameViewModel: BaseViewModel(){
    @Inject
    lateinit var apiService: ApiService

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val subscriptions = CompositeDisposable()
    private val errorLiveData = SingleLiveEvent<Void>()
    private val successLiveData = SingleLiveEvent<String>()

    fun sendRecord(name: String, record: Int){
        val json = JSONObject()
        json.put("record", record)
        val body = RequestBody.create(JSON_REQUEST_TYPE.toMediaTypeOrNull(), json.toString())

        subscriptions.add(apiService.usersRecord(body, name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading.value = true
            }
            .doOnTerminate {
                isLoading.value = false
            }
            .subscribe(
                {
                    successLiveData.call()
                },
                {
                    errorLiveData.call()
                }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}