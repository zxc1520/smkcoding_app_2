package com.smkcoding.myapplication.data

import com.smkcoding.myapplication.covid.Attributes
import com.smkcoding.myapplication.covid.CovidDataItemItem
import com.smkcoding.myapplication.covid19.CovidData
import retrofit2.Call
import retrofit2.http.GET

interface CovidGlobalDataService {

    @GET("api")
    fun getAttr(): Call<List<CovidData>>

}