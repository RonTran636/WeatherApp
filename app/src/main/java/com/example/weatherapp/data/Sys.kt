package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Sys(
    val country: String,
    val id: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    val type: Int
)