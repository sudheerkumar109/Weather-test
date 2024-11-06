package com.example.weathertest.data.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
//taking only required piece of data
data class WeatherResponse (
    val weather: List<Weather>,
    val main: WeatherMain,
    val name: String,
    @Json(name = "cod") val code : Int
)