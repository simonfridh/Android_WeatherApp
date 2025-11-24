package com.example.weather.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun CurrentWeatherBox(
    weatherState: WeatherState
){
    val current = weatherState.forecast?.currentWeather
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f/0.45f)
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
                text = "Current Weather",
                fontSize = 20.sp
            )
            if(current != null) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp,0.dp,8.dp,8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = current.weatherIcon.icon),
                        contentDescription = "WeatherIcon",
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f),
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(20.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${current.temperature}°C",
                            fontSize = 36.sp
                        )
                    }
                }
            }
        }
    }
}