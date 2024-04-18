package com.example.weather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R

@Composable
fun CityDetails(
    iconName: String,
    place:String,
    temp: String,
    message: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = place,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(vertical = 15.dp)
        )
        IconOverText(iconName = iconName, text = "$temp°")
        Text(
            text = message,
            fontSize = 22.sp,
            style = MaterialTheme.typography.labelLarge
        )
    }
}


@Composable
fun WeatherDetails(
    speed: String,
    humidity: String,
    cloudiness: String
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        RowIconText(icon = R.drawable.air, text = "$speed km/h")
        RowIconText(icon = R.drawable.humidilty, text = "$humidity%")
        RowIconText(icon = R.drawable.cloudy, text = "$cloudiness%")
    }
}

@Preview(showBackground = true)
@Composable
fun CityDetailsPreview(
){
    CityDetails(iconName = "Clear", place = "nairobi", temp = "78", message = "rain")
//    WeatherDetails(speed = "11km/hr", humidity = "02%", time = "8hr")
}