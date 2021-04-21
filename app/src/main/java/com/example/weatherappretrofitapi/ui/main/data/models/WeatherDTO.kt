package com.example.weatherappretrofitapi.ui.main.data.models

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("main")val weather: String,
    @SerializedName("description")val description: String,
    @SerializedName("icon")val iconUrl: String
)