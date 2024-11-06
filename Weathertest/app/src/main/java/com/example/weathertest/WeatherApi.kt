package com.example.weathertest

import com.example.weathertest.data.CitiesResponse
import com.example.weathertest.data.City
import com.example.weathertest.data.weather.WeatherResponse
import com.example.weathertest.networking.Either
import com.example.weathertest.networking.NetworkError
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    // Here we are just dealing with response. We can write code to handle error states
    @GET("geo/1.0/direct")
    suspend fun getCityResults(
        @Query("q") query: String,
        @Query("limit")  limit: Int,
        @Query("appid")  appKey : String
    ): Response<List<City>>

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat")  latitude: String,
        @Query("lon")  longitude: String,
        @Query("appid")  appKey : String
    ):Response<WeatherResponse?>
}