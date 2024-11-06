package com.example.weathertest.ui.data

import com.example.weathertest.data.City
import com.example.weathertest.data.weather.WeatherResponse

sealed interface WeatherScreenState {
 data object Loading : WeatherScreenState
    data object NoData: WeatherScreenState
    data class Success(
        val cities: List<City>,
        val weatherResponse: WeatherResponse?
    ): WeatherScreenState
}