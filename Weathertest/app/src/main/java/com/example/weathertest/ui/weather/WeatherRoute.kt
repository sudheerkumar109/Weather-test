package com.example.weathertest.ui.weather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Spinner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weathertest.ui.components.NoDataText
import com.example.weathertest.ui.components.ProgressIndicator
import com.example.weathertest.ui.data.WeatherScreenState
import com.example.weathertest.viewmodel.WeatherViewModel
import okhttp3.internal.concurrent.Task

@Composable
fun WeatherRoute(viewModel: WeatherViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            try {
                val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                location?.let {
                    viewModel.fetchWeatherForSelectedCity("${it.latitude}", "${it.longitude}")
               }
            } catch (_: Exception) {
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        val state = viewModel.state.collectAsStateWithLifecycle()
        val searchText = remember { mutableStateOf("") }
        TextField(
            value = searchText.value,
            onValueChange = { query ->
                searchText.value = query
                viewModel.onSearchQueryChanged(query)
                viewModel.clearSelectedCityWeather()
            },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        when(state.value){
            is WeatherScreenState.Loading ->{
                //ProgressIndicator()
            }
            is WeatherScreenState.NoData -> {
                NoDataText()
            }
            is WeatherScreenState.Success -> {
                val cities = (state.value as WeatherScreenState.Success).cities
                val weatherResponse = (state.value as WeatherScreenState.Success).weatherResponse
                if (weatherResponse == null) {
                        // Show city suggestions if no city has been selected
                        LazyColumn {
                            itemsIndexed(cities) { _,city ->
                                Text(
                                    text = "${city.name}, ${city.state ?: ""} ${city.country}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable {
                                            viewModel.fetchWeatherForSelectedCity(city.lat, city.lon)
                                        }
                                )
                            }
                        }
                    } else {
                        weatherResponse.let { weather ->
                            WeatherInfo(weather = weather)
                        }
                    }

            }
        }

    }

}
