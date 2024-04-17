package com.example.weather.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LocationNotFound(
    onDismissRequest: () -> Unit
){
    AlertDialog(
        onDismissRequest = { onDismissRequest()},
        confirmButton = {
                        Button(onClick = { onDismissRequest() }) {
                            Text(text = "okay")
                        }
                        },
        text = {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Location Not Found")
            }
        }
    )

}

@Preview
@Composable
fun LocationNotFoundPreview(){
    LocationNotFound {

    }
}