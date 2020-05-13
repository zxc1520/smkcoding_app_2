package com.smkcoding.myapplication.covid


import com.google.gson.annotations.SerializedName
import com.smkcoding.myapplication.covid.Attributes

data class CovidDataItemItem(
    @SerializedName("attributes")
    val attributes: Attributes
)