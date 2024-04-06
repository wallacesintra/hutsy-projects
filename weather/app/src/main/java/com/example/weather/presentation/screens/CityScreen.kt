package com.example.weather.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import com.example.weather.data.local.LocationEntity
import com.example.weather.presentation.components.CityComponent
import com.example.weather.presentation.models.CityState
import com.example.weather.presentation.models.CityUiState


@Composable
fun CityScreen(
    cityState: CityState,
    location: String,
    cityUiState: CityUiState,
//    locationList: List<LocationEntity>,
    onLocationChange: (String) -> Unit,
    onSearch: () -> Unit
){

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Your locations",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
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
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .height(50.dp)
                .zIndex(1f),
        )

            when(cityState){
                CityState.Error -> ErrorScreen()
                CityState.Loading -> LoadingScreen()
                is CityState.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 135.dp)
                    ) {
                        items(cityUiState.locationList){item ->
                            CityComponent(
                                iconName = item.mainWeather,
                                temp = item.temp,
                                name = "${item.name}, ${item.country}"
                            )
                        }
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