package com.smkcoding.myapplication.covid19


import com.google.gson.annotations.SerializedName

data class CountryDetail(
    @SerializedName("example")
    val example: String,
    @SerializedName("pattern")
    val pattern: String
)