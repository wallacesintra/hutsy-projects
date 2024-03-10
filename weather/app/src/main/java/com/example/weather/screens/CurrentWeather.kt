package com.example.weather.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.components.CityDetails
import com.example.weather.components.WeatherDetails

@Composable
fun CurrentScreen(

){
    Column {
        CityDetails("Nairobi, Kenya", "30 C", "It feels like it is going to rain")
        WeatherDetails(speed = "11km/hr", humidity = "02%", time = "8hr")
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentScreenPreview(){
    CurrentScreen()
}
