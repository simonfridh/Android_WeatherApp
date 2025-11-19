package com.example.weather.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
        "Söndag: varmt",
        "Måndag: kallt",
        "Tisdag: kallare",
        "Onsdag: kalle anka"
    )

    //PORTRAIT MODE
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            WeatherForecastList(
                modifier = Modifier.weight(1f),
                weatherForecasts = testWeatherForecasts
            ) {
                CurrentWeatherBox() {}
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
                CurrentWeatherBox(modifier = Modifier.weight(1f)) {}
                LongitudeLatitudeBar()
            }

            //RIGHT SIDE
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize()
            ) {
                WeatherForecastList(weatherForecasts = testWeatherForecasts) {}
            }
        }
    }
}

@Composable
fun CurrentWeatherBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    //TODO inte klar
    Box(
        modifier = modifier
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        //background image/icon
        Icon(
            painter = painterResource(id = R.drawable.snow),
            contentDescription = "WeatherIcon",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(3f / 2f),
            tint = Color.Unspecified
        )

        content()
    }
}

@Composable
fun WeatherForecastList(
    //TODO: Skicka någon form av lista hit
    modifier: Modifier = Modifier,
    weatherForecasts: List<String>,
    content: @Composable () -> Unit
) {
    LazyColumn (modifier = modifier) {
        //Nested content gets placed at the start of the list
        item {
            content()
        }

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
    HomeScreen()
}

@Preview(widthDp = 915, heightDp = 412)
@Composable
fun LandscapePreview() {
    HomeScreen()
}