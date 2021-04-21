package com.example.weatherappretrofitapi.ui.main.data.models

import com.google.gson.annotations.SerializedName

data class DailyReportDTO(
    @SerializedName("dt") val day: Long,
    @SerializedName("temp") val temperature: TemperatureDTO,
    @SerializedName("weather") val weather: List<WeatherDTO>
)