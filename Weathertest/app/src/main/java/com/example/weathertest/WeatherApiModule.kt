package com.example.weathertest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {

    @Provides
    fun providesWeatherApi(baseUrl : String,
                           retrofit: Retrofit) : WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }
}