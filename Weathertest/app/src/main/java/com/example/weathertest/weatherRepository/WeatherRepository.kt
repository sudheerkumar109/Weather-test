package com.example.weathertest.weatherRepository

import com.example.weathertest.data.CitiesResponse
import com.example.weathertest.data.City
import com.example.weathertest.data.weather.WeatherResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getCities(query: String, limit: Int, apiKey:String): Response<List<City>>

    suspend fun getWeather(  latitude: String,
                             longitude: String,
                             apiKey : String):Response<WeatherResponse?>
}