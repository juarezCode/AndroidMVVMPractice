package com.juarez.mvvmpractice.api

import com.juarez.mvvmpractice.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): Response<User>
}