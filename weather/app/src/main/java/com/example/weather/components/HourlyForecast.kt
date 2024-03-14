package com.example.weather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.WeatherData

@Composable
fun HourlyForecast(
    list: List<WeatherData.WeatherDetails>
){
    Column (
        Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.schedule),
                contentDescription = "hourly forecast",
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = "Hourly Forecast",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 18.sp
            )
        }

        LazyRow {
            items(list){item ->
                ColumnIconText(icon = R.drawable.snow, text1 = item.dt_txt.toString(), text2 = item.main.temp.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyForecastPreview(){
//    HourlyForecast()
}