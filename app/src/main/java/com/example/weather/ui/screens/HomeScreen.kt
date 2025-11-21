package com.example.weather.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.ui.viewmodel.FakeVM
import com.example.weather.ui.viewmodel.IWeatherViewModel
import com.example.weather.ui.viewmodel.WeatherState

@Composable
fun HomeScreen(
    vm: IWeatherViewModel
) {
    val orientation = LocalConfiguration.current.orientation
    val weatherState by vm.weatherState.collectAsState()

    LaunchedEffect(Unit) {
        vm.getForecast()
    }

    //PORTRAIT MODE
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            WeatherList(
                modifier = Modifier.weight(1f),
                weatherState = weatherState
            ) {
                CurrentWeatherBox(weatherState)
                HourlyWeatherBox(weatherState)
            }
            LongitudeLatitudeBar()
        }
    }

    //LANDSCAPE MODE
    if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            //LEFT SIDE
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxSize()
            ) {
                CurrentWeatherBox(weatherState)
                LongitudeLatitudeBar()
            }

            //RIGHT SIDE
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize()
            ) {
                WeatherList(weatherState = weatherState) {}
            }
        }
    }
}

@Composable
fun CurrentWeatherBox(
    weatherState: WeatherState
){
    val current = weatherState.forecast?.currentWeather
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f/1f)
            .padding(8.dp),
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
                fontSize = 24.sp
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

@Composable
fun HourlyWeatherBox(
    weatherState: WeatherState
){
    val hourlyWeatherList = weatherState.forecast?.hourlyWeather ?: emptyList()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f/1f)
            .padding(8.dp),
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
                fontSize = 24.sp
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

@Composable
fun WeatherList(
    modifier: Modifier = Modifier,
    weatherState: WeatherState,
    content: @Composable () -> Unit
) {
    LazyColumn (modifier = modifier) {
        //Nested content gets placed at the start of the list
        item {
            content()
        }

        val dailyWeatherList = weatherState.forecast?.dailyWeather ?: emptyList()
        items(dailyWeatherList){ weatherItem ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
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
}

@Composable
fun LongitudeLatitudeBar(
    modifier: Modifier = Modifier
) {
    var longitude by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .height(72.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ) {
        TextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp, 8.dp, 0.dp, 8.dp),
            shape = RoundedCornerShape(8.dp),
            value = longitude,
            onValueChange = {longitude = it},
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        )
        TextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp, 8.dp, 0.dp, 8.dp),
            shape = RoundedCornerShape(8.dp),
            value = latitude,
            onValueChange = {latitude = it},
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        )
        Button(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { /* TODO: Add button function */ }
        ) {
            Text("Button")
        }
    }

}




@Preview
@Composable
fun HomeScreenPreviewPortrait() {
    HomeScreen(FakeVM())
}

@Preview(widthDp = 915, heightDp = 412)
@Composable
fun LandscapePreview() {
    HomeScreen(FakeVM())
}