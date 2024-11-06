package com.example.weathertest.weatherRepository.concrete

import com.example.weathertest.WeatherApi
import com.example.weathertest.data.CitiesResponse
import com.example.weathertest.data.City
import com.example.weathertest.data.weather.WeatherResponse
import com.example.weathertest.networking.Either
import com.example.weathertest.networking.NetworkError
import com.example.weathertest.weatherRepository.WeatherRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl
@Inject
constructor(private val weatherApi: WeatherApi) : WeatherRepository
{
    override suspend fun getCities(
        query: String,
        limit: Int,
        apiKey: String
    ): Response<List<City>> {
        return weatherApi
            .getCityResults(query,limit,apiKey)

    }

    override suspend fun getWeather(
        latitude: String,
        longitude: String,
        apiKey: String
    ): Response<WeatherResponse?> {
        return weatherApi.getWeather(latitude, longitude, apiKey)
    }
}