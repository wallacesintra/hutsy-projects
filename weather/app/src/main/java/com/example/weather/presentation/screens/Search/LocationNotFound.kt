package com.example.weather.presentation.screens.Search

import android.text.Layout.Alignment
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LocationNotFound(
    onDismissRequest: () -> Unit
){
    AlertDialog(
        text = {
               Text(
                   text = "Location not found",
               )
        },
        onDismissRequest = { onDismissRequest() },
        buttons = {
            Button(onClick = { onDismissRequest()}) {
                Text(text = "Okay")
            }
        },
    )
}