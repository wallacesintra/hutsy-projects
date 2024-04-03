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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
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
import com.example.weather.presentation.events.CityActions
import com.example.weather.presentation.models.CityState
import com.example.weather.presentation.models.CityUiState
import com.example.weather.presentation.viewmodels.CityViewModel



@Composable
fun CityScreen(
    cityState: CityState,
    onSearch : (CityActions) -> Unit
){
    var location by remember {
        mutableStateOf("nairobi")
    }
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Your locations",
            fontSize = 20.sp,
//            modifier = Modifier.padding(8.dp)
        )
        TextField(
            leadingIcon = {
                          Icon(
                              imageVector = Icons.Default.Search,
                              contentDescription = "search location"
                          )
            },
            value = location ,
            keyboardActions = KeyboardActions(
                onDone = {
                         onSearch(CityActions.Search(location))
                },
                onSearch = {
                    onSearch(CityActions.Search(location))
                }
            ),
            onValueChange = {location = it},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .zIndex(1f),
        )

//        if (state.found){
//            CityComponent(
//                iconName =  state.list[0].weather[0].main,
//                temp = state.temp,
//                name = "${state.name}, ${state.country}"
//            )
//        } else {
//            Text(text = "location not found")
//        }

        when(cityState){
            CityState.Error -> ErrorScreen()
            CityState.Loading -> LoadingScreen()
            is CityState.Success -> {
                if (cityState.cityUiState.found){
                    CityComponent(
                        iconName = cityState.cityUiState.list[0].weather[0].main,
                        temp = cityState.cityUiState.temp,
                        name = "${cityState.cityUiState.name}, ${cityState.cityUiState.country}"
                    )
                } else {
                    Text(text = "Location not found")
                }
            }
        }

//        when (cityState){
//            CityState.Error -> ErrorScreen()
//            CityState.Loading -> LoadingScreen()
//            is CityState.Success -> {
//
//            }
//        }


    }
}

@Preview
@Composable
fun CityScreenPreview(){
//    CityScreen(
//        onSearch = { CityActions.Search("nairobi") },
//        state = CityUiState(
//        name = "Nairobi",
//        country = "Ke",
//        temp = "45",
//    ))
}