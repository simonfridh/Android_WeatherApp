package com.example.weather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.ui.viewmodel.WeatherState

@Composable
fun HourlyWeatherBox(
    weatherState: WeatherState
){
    val hourlyWeatherList = weatherState.forecast?.hourlyWeather ?: emptyList()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1/0.45f)
            .padding(8.dp,8.dp,8.dp,0.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Next 24h",
                fontSize = 20.sp
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(hourlyWeatherList) { weatherItem ->
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${weatherItem.time.toLocalTime()}",
                            fontSize = 16.sp
                        )
                        Icon(
                            painter = painterResource(id = weatherItem.weatherIcon.icon),
                            contentDescription = "WeatherIcon",
                            modifier = Modifier
                                .size(64.dp),
                            tint = Color.Unspecified
                        )
                        Text(
                            text = "${weatherItem.temperature}°C",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}