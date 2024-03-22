package com.example.networkfetch.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.networkfetch.R
import com.example.networkfetch.presentation.models.MarsUiState

@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
){
    when(marsUiState) {
        is MarsUiState.Success -> ResultScreen(photos = marsUiState.photos)
        is MarsUiState.Loading -> LoadingScreen()
        is MarsUiState.Error -> ErrorScreen()
    }

}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen() {
    Image(
        modifier = Modifier.size(200.dp),
        painter = painterResource(R.drawable.cloudy),
        contentDescription = "Loading"
    )

}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.network), contentDescription = ""
        )
        Text(text = "Failed", modifier = Modifier.padding(16.dp))
    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
        LoadingScreen()
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
        ErrorScreen()

}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
        ResultScreen("Success")
}