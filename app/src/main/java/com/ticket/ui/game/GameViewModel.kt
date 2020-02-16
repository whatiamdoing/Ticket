package com.ticket.ui.game

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

    private val subscriptions = CompositeDisposable()
    val errorLiveData = SingleLiveEvent<Void>()
    val succsessLiveData = SingleLiveEvent<Void>()

    fun sendRecord(id: String, record: Int){
        val json = JSONObject()
        json.put("record", record)
        val body = RequestBody.create(JSON_REQUEST_TYPE.toMediaTypeOrNull(), json.toString())

        subscriptions.add(
            apiService.setUserRecord(body, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {}
            .doOnTerminate {}
            .subscribe({},
                {errorLiveData.call()}))
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}