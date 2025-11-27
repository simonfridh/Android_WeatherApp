package com.example.weather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.ui.viewmodel.FakeVM

@Composable
fun PlacenameBar(
    onSubmit: (placename: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var placename by remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .widthIn(max = 500.dp), //Max width of this component is 500dp
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = placename,
                onValueChange = { placename = it },
                placeholder = { Text("Placename") },
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(fontSize = 20.sp),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                modifier = Modifier.height(56.dp),
                shape = RoundedCornerShape(8.dp),
                enabled = !placename.isEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  MaterialTheme.colorScheme.primary,
                    contentColor =  MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    onSubmit(placename)
                }
            ) {
                Text("SUBMIT")
            }
        }
    }
}

@Preview
@Composable
private fun PlacenameBarPreview() {
    PlacenameBar(onSubmit = FakeVM()::getForecast)
}