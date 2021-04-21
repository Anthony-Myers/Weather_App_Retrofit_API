package com.example.weatherappretrofitapi.ui.main.data.models

import com.google.gson.annotations.SerializedName

data class TemperatureDTO(
    @SerializedName("day") val dayTemp: Double,
    @SerializedName("min") val minTemp: Double,
    @SerializedName("max") val maxTemp: Double
)