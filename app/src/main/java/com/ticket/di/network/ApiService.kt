package com.ticket.di.network


import com.ticket.entity.User
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @PUT("users/{id}.json")
    fun createUser(@Path("id") id: String, @Body user: User): Observable<Response<ResponseBody>>

    @PATCH("users/{id}/.json")
    fun setUserRecord(@Body body: RequestBody, @Path("id") id: String): Observable<Response<ResponseBody>>

    @GET("users.json")
    fun getUsers(): Observable<Map<String, User>>

    @PATCH("users/{id}/.json")
    fun changeUserName(@Path("id") id: String, @Body body: RequestBody): Observable<Response<ResponseBody>>
}