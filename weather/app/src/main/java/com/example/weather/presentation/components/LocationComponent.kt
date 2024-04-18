package com.example.weather.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
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
import com.example.weather.data.local.LocationEntity
import com.example.weather.presentation.utils.drawableMap

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationComponent(
    locationEntity: LocationEntity,
    onClick: (LocationEntity) -> Unit
){
    val icon = drawableMap[locationEntity.mainWeather] ?: R.drawable.ic_launcher_foreground

    Card(
        modifier = Modifier
            .padding(4.dp)
            .combinedClickable(
                onLongClickLabel = "delete location",
                onLongClick = {
                    onClick(locationEntity)
                },
                onClick = {}
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(4.dp)
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "weather icon",
                    modifier = Modifier
                        .zIndex(1f)
                        .size(60.dp)
                        .align(Alignment.BottomEnd)
                )
                Text(
                    text = "${locationEntity.temp}°",
                    fontFamily = FontFamily.Serif,
                    fontSize = 80.sp,
                    modifier = Modifier
                        .zIndex(2f)
//                        .align(Alignment.BottomStart)
                )
            }
            Text(
                text = "${locationEntity.name},${locationEntity.country}",
                fontSize = 18.sp
            )
        }

    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewCityComponent(){
    LocationComponent(locationEntity = LocationEntity("mombasa", "kenya", "23", "Rain")){}
}
