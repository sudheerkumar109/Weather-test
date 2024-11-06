package com.example.weathertest.data.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "id") val weatherId: Int,
    val main : String,
    val description : String,
    val icon: String
)