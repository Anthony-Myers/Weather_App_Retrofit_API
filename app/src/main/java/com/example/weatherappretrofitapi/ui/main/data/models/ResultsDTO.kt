package com.example.weatherappretrofitapi.ui.main.data.models

import com.google.gson.annotations.SerializedName

data class ResultsDTO(
    @SerializedName("current") val currentWeather: CurHourlyReportDTO,
    @SerializedName("hourly") val hourlyWeather: List<CurHourlyReportDTO>,
    @SerializedName("daily") val dailyWeather: List<DailyReportDTO>
)