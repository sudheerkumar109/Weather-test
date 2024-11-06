package com.example.weathertest

import com.example.weathertest.weatherRepository.WeatherRepository
import com.example.weathertest.weatherRepository.concrete.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface WeatherRepositoryModule {
    @Binds fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}