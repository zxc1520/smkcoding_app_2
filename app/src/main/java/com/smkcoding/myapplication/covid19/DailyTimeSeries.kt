package com.smkcoding.myapplication.covid19


import com.google.gson.annotations.SerializedName

data class DailyTimeSeries(
    @SerializedName("example")
    val example: String,
    @SerializedName("pattern")
    val pattern: String
)