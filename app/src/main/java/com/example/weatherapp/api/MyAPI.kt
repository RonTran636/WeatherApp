package com.example.weatherapp.api

import com.example.weatherapp.data.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MyAPI {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon:String,
        @Query("appid") appID: String,
        @Query("units") units: String
    ) : Call<WeatherInfo>
}