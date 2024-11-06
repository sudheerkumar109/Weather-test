package com.example.weathertest.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weathertest.data.weather.WeatherResponse

@Composable
fun WeatherInfo(weather: WeatherResponse) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Weather in ${weather.name}", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))

        val temperature = (weather.main.temp - 273.15) * 9/5 + 32
        Text(text = "Temperature: %.1f°F".format(temperature))

        Text(text = "Feels like: %.1f°F".format((weather.main.feelsLike - 273.15) * 9/5 + 32))
        Text(text = "Humidity: ${weather.main.humidity}%")

        weather.weather.firstOrNull()?.let {
            Row {
                Text(modifier = Modifier.align(Alignment.CenterVertically), text = it.description)
                Image(
                    painter = rememberAsyncImagePainter(
                         "https://openweathermap.org/img/wn/${it.icon}@2x.png"
                    ),
                    contentDescription = "Weather icon",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(38.dp)
                        .align(Alignment.CenterVertically)
                )
            }

        }
    }
}
