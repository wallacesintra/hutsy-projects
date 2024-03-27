package com.example.networkfetch.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.networkfetch.R
import com.example.networkfetch.network.MarsPhoto
import com.example.networkfetch.presentation.models.MarsUiState

@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
){
    when(marsUiState) {
        is MarsUiState.Success -> MarsPhotoCard(photo = marsUiState.photos, modifier = Modifier.fillMaxWidth())
        is MarsUiState.Loading -> LoadingScreen()
        is MarsUiState.Error -> ErrorScreen()
    }

}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.loading),
            contentDescription = "Loading",
//            contentScale = ContentScale.Crop
            modifier = Modifier.size(70.dp),
        )
    }

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

@Composable
fun MarsPhotoCard(
    photo: MarsPhoto,
    modifier: Modifier = Modifier
){
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(photo.imgSrc)
            .crossfade(true)
            .build(),
        contentDescription = "mar photo",
        modifier = Modifier.fillMaxWidth()
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingScreenPreview() {
        LoadingScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorScreenPreview() {
        ErrorScreen()

}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
        ResultScreen("Success")
}