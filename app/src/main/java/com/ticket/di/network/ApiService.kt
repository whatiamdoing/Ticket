package com.ticket.di.network

import com.ticket.di.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}