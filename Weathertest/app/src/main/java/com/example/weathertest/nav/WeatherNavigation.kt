package com.example.weathertest.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weathertest.ui.landing.LandingScreen
import com.example.weathertest.ui.weather.WeatherRoute
import com.example.weathertest.viewmodel.WeatherViewModel

fun NavController.navigateToWeather(){
    navigate(WeatherScreenDestination.route)
}

fun NavGraphBuilder.weatherGraph(navigateToWeather: () -> Unit,
                                 weatherViewModel: WeatherViewModel){

    composable(route = LandingScreenDestination.route){
        LandingScreen(navigateToWeather = navigateToWeather,
            viewModel = weatherViewModel)
    }
    composable(route = WeatherScreenDestination.route){
        WeatherRoute()
    }

}