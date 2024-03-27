package com.example.networkfetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.networkfetch.presentation.screens.HomeScreen
import com.example.networkfetch.presentation.viewmodel.MarsViewModel
import com.example.networkfetch.ui.theme.NetworkFetchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkFetchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    Container()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Container(){
    val viewModel: MarsViewModel = viewModel(factory = MarsViewModel.Factory)
    HomeScreen(marsUiState = viewModel.marsUiState)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NetworkFetchTheme {
        Greeting("Android")
    }
}