package com.example.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R

@Composable
fun CityDetails(
//    modifier : Modifier = Modifier,
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
                .padding(10.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.sun ),
            contentDescription = "weather icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = temp,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 32.sp,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )

        Text(
            text = message,
            fontSize = 18.sp
        )
    }
}

@Composable
fun WeatherDetails(
    speed: String,
    humidity: String,
    time: String
){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier =  Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        RowIconText(icon = R.drawable.air, text = speed, 34, 26)
        RowIconText(icon = R.drawable.humidilty, text = humidity, 34, 26)
        RowIconText(icon = R.drawable.sunny, text = time, 34, 26)
    }
}

@Preview(showBackground = true)
@Composable
fun CityDetailsPreview(
){
//    CityDetails(
//        place = "Nairobi, Kenya",  temp = "30 C", message = "It feels like it going to rain"
//    )
    WeatherDetails(speed = "11km/hr", humidity = "02%", time = "8hr")
}