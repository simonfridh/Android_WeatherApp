package com.example.weather.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R


@Composable
fun HomeScreen(

) {
    val orientation = LocalConfiguration.current.orientation

    //TODO Byt ut mot något ordentligt
    val testWeatherForecasts = listOf(
        "Måndag: kallt",
        "Tisdag: kallare",
        "Onsdag: kalle anka",
        "Torsdag: Slask",
        "Fredag: SOL!",
        "Lördag: Slask :(",
        "Söndag: varmt"
    )

    //PORTRAIT MODE
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //Top section
            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CurrentWeatherBox()
            }

            //Bottom section
            Column(
                modifier = Modifier
                    .weight(0.65f)
                    .fillMaxSize(),
            ) {
                WeatherForecastList(testWeatherForecasts)
            }
        }

    }

    //LANDSCAPE MODE
    if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            //Right side
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CurrentWeatherBox()
            }

            //Left side
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize()
            ) {
                WeatherForecastList(testWeatherForecasts)
            }

        }
    }
}

@Composable
fun CurrentWeatherBox(

){
    //TODO inte klar
    Icon(
        painter = painterResource(id = R.drawable.snow),
        contentDescription = "WeatherIcon",
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(3f / 2f),
        tint = Color.Unspecified
    )
}


@Composable
fun WeatherForecastList(
    //TODO: Skicka någon form av lista hit
    weatherForecasts: List<String>
) {
    LazyColumn {
        //TODO Lägg till någon grupperad vy här sen. Kanske en till card med olika tider

        //Simple List
        items(weatherForecasts){ forecast ->
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
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = "WeatherIcon",
                        modifier = Modifier
                            .size(64.dp)
                            .aspectRatio(3f / 2f),
                        tint = Color.Unspecified
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = forecast,
                        fontSize = 30.sp
                    )
                }
            }
        }
    }
}




@Preview
@Composable
fun HomeScreenPreviewPortrait() {
    HomeScreen()
}

@Preview(widthDp = 915, heightDp = 412)
@Composable
fun LandscapePreview() {
    HomeScreen()
}