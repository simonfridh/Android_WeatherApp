package com.example.weather.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.ui.components.CurrentWeatherBox
import com.example.weather.ui.components.HourlyWeatherBox
import com.example.weather.ui.components.LongitudeLatitudeBar
import com.example.weather.ui.components.NoDataBox
import com.example.weather.ui.components.PopupMessage
import com.example.weather.ui.components.WeatherList
import com.example.weather.ui.viewmodel.FakeVM
import com.example.weather.ui.viewmodel.IWeatherViewModel
import com.example.weather.ui.viewmodel.UiEvent

@Composable
fun HomeScreen(
    vm: IWeatherViewModel
) {
    val orientation = LocalConfiguration.current.orientation
    val weatherState by vm.weatherState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    //Load event channel on launch
    LaunchedEffect(Unit){
        vm.events.collect { event ->
            when (event) {
                is UiEvent.ShowPopup -> {
                    snackBarHostState.showSnackbar(event.msg)
                }
            }
        }
    }

    Scaffold(
        //Popup message
        snackbarHost = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                SnackbarHost(
                    hostState = snackBarHostState,
                    snackbar = { data -> PopupMessage(data) }
                )
            }
        }
    ) { padding ->

        //PORTRAIT MODE
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            Column(
                modifier = Modifier
                    .padding(paddingValues = padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    if(weatherState.forecast != null) {
                        WeatherList(
                            weatherState = weatherState
                        ) {
                            CurrentWeatherBox(weatherState)
                            Spacer(modifier = Modifier.height(8.dp))
                            HourlyWeatherBox(weatherState)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    else{
                        NoDataBox()
                    }
                }

                LongitudeLatitudeBar(
                    onSubmit = vm::getForecast,
                    modifier = Modifier.padding(8.dp,8.dp,8.dp,0.dp)
                )
            }
        }

        //LANDSCAPE MODE
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Column(modifier = Modifier.padding(paddingValues = padding)) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.Top
                ) {
                    if(weatherState.forecast != null){
                        //LEFT SIDE
                        Column(
                            modifier = Modifier
                                .weight(0.40f)
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            CurrentWeatherBox(
                                weatherState = weatherState,
                                modifier = Modifier
                                    .weight(0.45f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            HourlyWeatherBox(
                                weatherState = weatherState,
                                modifier = Modifier.weight(0.55f)
                            )
                        }
                        //RIGHT SIDE
                        Card(
                            modifier = Modifier
                                .weight(0.60f)
                                .fillMaxSize()
                                .padding(8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )
                        ) {
                            WeatherList(weatherState) {}
                        }
                    }
                    else {
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "No data",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    LongitudeLatitudeBar(
                        onSubmit = vm::getForecast,
                        modifier = Modifier.padding(8.dp,8.dp,8.dp,0.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PortraitPreview() {
    HomeScreen(FakeVM())
}

@Preview(widthDp = 915, heightDp = 412)
@Composable
private fun LandscapePreview() {
    HomeScreen(FakeVM())
}