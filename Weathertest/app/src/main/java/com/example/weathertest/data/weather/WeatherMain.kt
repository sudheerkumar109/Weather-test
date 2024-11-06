package com.example.weathertest.data.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherMain (
    val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "temp_min") val minTemperature: Double,
    @Json(name = "temp_max") val maxTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    @Json(name = "sea_level") val seaLevel: Int,
    @Json(name = "grnd_level") val grnd_level: Int
)