package com.example.weather.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.weather.presentation.components.CityComponent
import com.example.weather.presentation.models.CityState


@Composable
fun CityScreen(
    cityState: CityState,
    location: String,
    onLocationChange: (String) -> Unit,
    onSearch: () -> Unit
){

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
            value = location,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            onValueChange = {onLocationChange(it)},

            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .zIndex(1f),
        )

        if (location != ""){
            when(cityState){
                CityState.Error -> ErrorScreen()
                CityState.Loading -> LoadingScreen()
                is CityState.Success -> {
                    CityComponent(
//                        iconName = cityState.cityUiState.list[0].weather[0].main,
                        iconName = cityState.cityUiState.mainWeather,
                        temp = cityState.cityUiState.temp,
                        name = "${cityState.cityUiState.name}, ${cityState.cityUiState.country}"
                    )
                }
            }
        }

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