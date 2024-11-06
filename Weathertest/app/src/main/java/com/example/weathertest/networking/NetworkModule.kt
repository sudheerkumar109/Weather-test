package com.example.weathertest.networking

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    //Can configure url string in constants or fetch from config
    @Singleton
    @Provides
    fun provideBaseUrl():String = "https://api.openweathermap.org/"


    @Provides
    @Singleton
    fun provideMoshi():Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideRetrofit(baseUrl:String, moshi: Moshi):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}