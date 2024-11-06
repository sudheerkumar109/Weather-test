package com.example.weathertest.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertest.data.City
import com.example.weathertest.data.weather.WeatherResponse
import com.example.weathertest.ui.data.WeatherScreenState
import com.example.weathertest.weatherRepository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

const val LIMIT = 10
//can avoid saving key locally or encrypt using tools like dexguard
const val API_KEY = "ee135592282ed538f57d5466f5e36efe"
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
):ViewModel() {

    private val _suggestions = MutableStateFlow<List<City>>(listOf())

    private val _searchQuery = MutableStateFlow("")

    private val _cityWeather = MutableStateFlow<WeatherResponse?>(null)

    val state = combine(_suggestions,_cityWeather){ suggestions, cityWeather ->
        if (suggestions.isNotEmpty() || cityWeather != null){
            WeatherScreenState.Success(cities = suggestions, weatherResponse = cityWeather)
        }else{
            WeatherScreenState.NoData
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = WeatherScreenState.Loading
    )

    init {
        observeSearchQuery()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun clearSelectedCityWeather() {
        _cityWeather.value = null
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .filter { it.isNotEmpty() }
                .collect { query ->
                    fetchCitySuggestions(query)
                }
        }
    }

    private suspend fun fetchCitySuggestions(query: String) {
        val suggestions = weatherRepository.getCities(query = "$query,US",
            limit = LIMIT,
            API_KEY)
        if (suggestions.isSuccessful)
            _suggestions.value = suggestions.body() ?: listOf()
        else
            _suggestions.value = listOf()
    }

    fun fetchWeatherForSelectedCity(latitude: String, longitude: String) {
        viewModelScope.launch {
            val weather = weatherRepository.getWeather(latitude, longitude, API_KEY)
            if (weather.isSuccessful)
                _cityWeather.value = weather.body()
            else
                _cityWeather.value = null
        }
    }


}