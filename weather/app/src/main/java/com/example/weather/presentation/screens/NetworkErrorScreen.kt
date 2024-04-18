package com.example.weather.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R

@Composable
fun NetworkErrorScreen(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.not_connected),
            contentDescription = stringResource(id = R.string.connect_internet),
            modifier = Modifier
                .size(70.dp)
                .padding(8.dp)
        )
        androidx.compose.material3.Text(
            text = stringResource(id = R.string.connect_internet),
            fontSize = 18.sp
        )
    }
}