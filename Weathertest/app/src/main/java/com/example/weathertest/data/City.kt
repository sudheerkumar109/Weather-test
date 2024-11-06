package com.example.weathertest.data

//keeping lat, lon as string instead of Double
data class City (val name : String,
                 val lat : String,
                 val lon : String,
                 val country: String,
                 val state: String)