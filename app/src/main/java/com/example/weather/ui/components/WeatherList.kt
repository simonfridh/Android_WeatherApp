package com.example.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.ui.viewmodel.FakeVM
import com.example.weather.ui.viewmodel.WeatherState

@Composable
fun WeatherList(
    weatherState: WeatherState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val dailyWeatherList = weatherState.forecast?.dailyWeather ?: emptyList()

    LazyColumn {
        //Nested content gets placed at the start of the list
        item {
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }

        //Next N-days
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                //Text at top
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Next ${dailyWeatherList.size} days",
                        fontSize = 20.sp
                    )
                }

                //Weather data
                for (weatherItem in dailyWeatherList) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = weatherItem.weatherIcon.icon),
                            contentDescription = "WeatherIcon",
                            modifier = Modifier
                                .size(64.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = weatherItem.time.dayOfWeek.toString(),
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = weatherItem.time.toLocalDate().toString(),
                                    fontSize = 16.sp
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "min: ${weatherItem.minTemperature}°C",
                                    fontSize = 24.sp
                                )
                                Text(
                                    text = "max: ${weatherItem.maxTemperature}°C",
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
private fun WeatherListPreview() {
    WeatherList(
        weatherState = FakeVM().weatherState.value
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text("Test Content")
        }
    }
}

