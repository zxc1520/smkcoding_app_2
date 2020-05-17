package com.smkcoding.myapplication.covid


import com.google.gson.annotations.SerializedName

data class ProvinsiItem(
    @SerializedName("attributes")
    val attributes: Attributes
)