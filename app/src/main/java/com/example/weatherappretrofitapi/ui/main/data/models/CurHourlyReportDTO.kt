package com.example.weatherappretrofitapi.ui.main.data.models

import com.google.gson.annotations.SerializedName

data class CurHourlyReportDTO(
    @SerializedName("dt") val time: Long,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("weather") val weather: List<WeatherDTO>
)