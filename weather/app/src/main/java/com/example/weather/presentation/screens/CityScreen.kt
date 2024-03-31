package com.example.weather.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.weather.R
import com.example.weather.presentation.components.CityComponent
import com.example.weather.presentation.models.CityUiState
import com.example.weather.presentation.viewmodels.CityViewModel



@Composable
fun CityScreen(
    state: CityUiState
){
    var location by remember {
        mutableStateOf("")
    }
    Column(
    ) {
        Text(
            text = "Your locations",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Box {
            TextField(
                value = location ,
                onValueChange = {location = it},
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
                    .zIndex(1f),
            )

            Image(
                painter = painterResource(id =R.drawable.map ),
                contentDescription = "Map icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(
                        height = 60.dp,
                        width = 60.dp
                    )
                    .zIndex(2f)
            )
        }

        CityComponent(
            iconName =  state.list[0].weather[0].main,
            temp = state.temp,
            name = "${state.name}, ${state.country}"
        )

    }
}

@Preview
@Composable
fun CityScreenPreview(){
    CityScreen(state = CityUiState(
        name = "Nairobi",
        country = "Ke",
        temp = "45"
    ))
}