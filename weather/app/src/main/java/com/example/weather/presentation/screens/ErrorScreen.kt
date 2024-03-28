package com.example.weather.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(){
    Text(text = "Not connected")
}

@Preview
@Composable
fun PreviewErrorScreen(){
    ErrorScreen()
}