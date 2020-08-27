package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.example.weatherapp.api.MyAPI
import com.example.weatherapp.data.WeatherInfo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://api.openweathermap.org/"
    private val retrofit: Retrofit
    private var lat = "10.76"
    private var lon = "106.66"
    private val appID = "91e6660597061b5c0b7ec8c39070700a"
    private var units = "metric"
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCurrentData()
    }

    private fun getCurrentData() {
        val service = retrofit.create(MyAPI::class.java)
        val call: Call<WeatherInfo> = service.getCurrentWeatherData(lat, lon, appID,units)
        call.enqueue(object : Callback<WeatherInfo> {
            override
            fun onResponse(@NonNull call: Call<WeatherInfo>, @NonNull response: Response<WeatherInfo>) {
                val weatherResponse = response.body()!!
                //Info
                address.text =
                    weatherResponse.name
                status.text =
                    weatherResponse.weather[0].main
                temp.text =
                    weatherResponse.main.temp.toString()
                updated_at.text =
                    "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(weatherResponse.dt.toLong()*1000))
                sunrise.text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(weatherResponse.sys.sunrise.toLong()*1000))
                sunset.text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(weatherResponse.sys.sunset.toLong()*1000))
                wind.text =
                    weatherResponse.wind.speed.toString()
                humidity.text =
                    weatherResponse.main.humidity.toString()
                pressure.text =
                    weatherResponse.main.pressure.toString()
            }

            override
            fun onFailure(@NonNull call: Call<WeatherInfo>, @NonNull t: Throwable) {
            }
        })
    }

}