package com.ticket.di.network

import com.ticket.entity.User
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @PUT("/{name}.json")
    fun createUser(@Path("name") name: String, @Body user: User): Observable<Response<ResponseBody>>
}