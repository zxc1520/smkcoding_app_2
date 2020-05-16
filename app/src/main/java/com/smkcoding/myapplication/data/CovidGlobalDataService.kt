package com.smkcoding.myapplication.data

import com.smkcoding.myapplication.covid.IndonesiaModel
import com.smkcoding.myapplication.covid.ProvinsiModel
import com.smkcoding.myapplication.covid19.CovidData
import retrofit2.Call
import retrofit2.http.GET

interface CovidGlobalDataService {

    @GET("indonesia")
    fun getAttr(): Call<List<IndonesiaModel>>

    @GET("indonesia/provinsi")
    fun getProv(): Call<List<ProvinsiModel>>


}