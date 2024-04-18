package com.example.weather.presentation.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.network.dto.CurrentWeatherData
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HourlyForecast(
    list: List<CurrentWeatherData.WeatherList>
){

    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hha", Locale.getDefault())

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
                contentDescription = "Next forecast",
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = stringResource(id = R.string.forecast),
                style = MaterialTheme.typography.labelLarge,
                fontSize = 20.sp
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(items = list.take(9)) { item ->
                val date = inputFormat.parse(item.dtTxt)
                val formattedDate = if (date != null) outputFormat.format(date) else "Invalid date"

                ColumnIconText(
                    iconName = item.weather[0].main,
                    text1 = formattedDate,
                    text2 = "${item.main.temp.toInt()}\u00B0"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyForecastPreview(){
//    HourlyForecast()
}