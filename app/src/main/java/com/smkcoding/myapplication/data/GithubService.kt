package com.smkcoding.myapplication.data

import com.smkcoding.myapplication.GithubUserItem
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("users")
    fun getUsers() : Call<List<GithubUserItem>>

}