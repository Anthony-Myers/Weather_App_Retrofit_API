package com.example.weatherappretrofitapi.ui.main.data.remote.network

import com.example.weatherappretrofitapi.ui.main.data.models.ResultsDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherManager {
    private val service: WeatherService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(WeatherService::class.java)
    }

    fun getWeather(lat: Double, lon: Double, appid: String, exclude: String) =
        service.getWeather(lat, lon, appid, exclude)

    interface WeatherService {
        @GET("/data/2.5/onecall")
        fun getWeather(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("appid") appid: String,
            @Query("exclude") exclude: String
        ) : Single<ResultsDTO>
    }
}