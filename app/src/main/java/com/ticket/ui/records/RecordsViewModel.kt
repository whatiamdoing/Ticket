package com.ticket.ui.records

import androidx.lifecycle.MutableLiveData
import com.ticket.base.BaseViewModel
import com.ticket.di.network.ApiService
import com.ticket.entity.User
import com.ticket.entity.UserDTO
import com.ticket.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecordsViewModel: BaseViewModel(){

    @Inject
    lateinit var apiService: ApiService

    private val subscriptions = CompositeDisposable()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData = SingleLiveEvent<Void>()
    val successLiveData = SingleLiveEvent<String>()
    var users = MutableLiveData<List<UserDTO>>()

    fun loadUsers(){
        subscriptions.add(apiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {isLoading.value = true }
            .doOnTerminate {isLoading.value = false}
            .subscribe(
                { (onRetrieveUserListSuccess(it))
                successLiveData.call()},
                { errorLiveData.call()}
            ))
    }

    private fun onRetrieveUserListSuccess(it: Map<String, User>): List<UserDTO> {
        val userList = it.map {
            UserDTO(it.key, it.value.id, it.value.record)
        }.sortedByDescending{ it.record }
        users.value = userList
        return userList
    }
}