package com.example.weatherappretrofitapi.ui.main.data.repos

import com.example.weatherappretrofitapi.ui.main.data.models.ResultsDTO
import com.example.weatherappretrofitapi.ui.main.data.remote.network.WeatherManager
import io.reactivex.Single

class WeatherRepo {
    fun getWeather(): Single<ResultsDTO> {
        return WeatherManager().getWeather(39.93, -75.18, "a70e681605029a266c175b03ce8917ed", "minutely")
    }
}