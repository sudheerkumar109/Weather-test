package com.example.weathertest.ui.landing

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weathertest.ui.permissions.LocationPermissionRequest
import com.example.weathertest.viewmodel.WeatherViewModel


@Composable
fun LandingScreen(navigateToWeather:() -> Unit,
                  viewModel: WeatherViewModel){
    LocationPermissionRequest(
        onPermissionGranted = {
            //
            navigateToWeather()
        },
        onPermissionDenied = {
            //
            navigateToWeather()
        }
    )
    Box {
        Text(modifier = Modifier.align(Alignment.Center), text = "Welcome to the Weather App")
    }

}