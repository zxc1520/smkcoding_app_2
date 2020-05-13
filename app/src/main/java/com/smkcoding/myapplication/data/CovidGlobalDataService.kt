package com.smkcoding.myapplication.data

import com.smkcoding.myapplication.covid.Attributes
import com.smkcoding.myapplication.covid.CovidDataItemItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidGlobalDataService {
    @GET("atrributes")
    fun getAttr(): Call<List<Attributes>>

}