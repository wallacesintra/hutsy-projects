package com.example.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.weather.R

@Composable
fun CityComponent(
    iconName: String,
    temp: String,
    name: String
){
    val icon = drawableMap[iconName] ?: R.drawable.ic_launcher_foreground

    Card(
        modifier = Modifier
            .padding(8.dp)
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
                    text = "$temp°",
                    fontFamily = FontFamily.Serif,
                    fontSize = 80.sp,
                    modifier = Modifier
                        .zIndex(2f)
//                        .align(Alignment.BottomStart)
                )
            }
            Text(
                text = name,
                fontSize = 18.sp
            )
        }

    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewCityComponent(){
}
