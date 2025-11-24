package com.example.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LongitudeLatitudeBar(
    modifier: Modifier = Modifier
) {
    var longitude by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ) {
        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(2.dp, 2.dp, 2.dp, 2.dp),
            shape = RoundedCornerShape(8.dp),
            value = longitude,
            onValueChange = {longitude = it},
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        )
        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(2.dp, 2.dp, 2.dp, 2.dp),
            shape = RoundedCornerShape(8.dp),
            value = latitude,
            onValueChange = {latitude = it},
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        )
        Button(
            modifier = Modifier
                .fillMaxHeight()
                .padding(2.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { /* TODO: Add button function */ }
        ) {
            Text("Button")
        }
    }
}