package com.example.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NoDataBox(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "No data",
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun NoDataBoxPreview() {
    //Wrapped in box with background because text was hard to see in preview :D
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        NoDataBox()
    }
}

