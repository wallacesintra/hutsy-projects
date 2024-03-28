package com.example.weather.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.weather.R
import com.example.weather.presentation.components.drawableMap
import com.example.weather.data.demo.WeatherData
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ForecastScreen(
    list: List<WeatherData.WeatherDetails>
){
    Column {
        Text(
            text = "Next 5 days",
            fontSize = 22.sp,
//            fontStyle = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn {
            items(list) {item ->
                ForecastItem(item = item)
            }
        }
    }

}
@Composable
fun ForecastItem(
    item: WeatherData.WeatherDetails
){
    val dayFormat = SimpleDateFormat("E,", Locale.getDefault())
    val hourFormat = SimpleDateFormat("hha", Locale.getDefault())
    val icon = drawableMap[item.weather[0].main] ?: R.drawable.ic_launcher_foreground
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = "weather icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(height = 80.dp, width = 100.dp)
                    .zIndex(2f)
            )
            Card(
                modifier = Modifier
                    .height(80.dp)
                    .zIndex(1f),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth(0.95f)
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = dayFormat.format(item.dt_txt)
                        )
                        Text(
                            text = hourFormat.format(item.dt_txt)
                        )
                    }
                    Spacer(modifier = Modifier.fillMaxWidth(0.25f))
                    Column {
                        Text(
                            text = item.weather[0].description,
                            style = MaterialTheme.typography.labelLarge,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "${item.main.temp.toInt().toString()}°",
                            style = MaterialTheme.typography.labelLarge,
                            fontSize = 40.sp,
                            fontFamily = FontFamily.Serif
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastPreview(){
//    ForecastItem()
}