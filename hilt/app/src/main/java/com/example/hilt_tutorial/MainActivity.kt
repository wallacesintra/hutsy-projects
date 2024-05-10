package com.example.hilt_tutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hilt_tutorial.presentation.models.PostState
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hilt_tutorial.presentation.viewmodel.PostViewModel
import com.example.hilt_tutorial.ui.theme.HilttutorialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HilttutorialTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }

                val postViewModel = hiltViewModel<PostViewModel>()
                
                val userList = postViewModel.userList
//                when(val postState = postViewModel.postState){
//                    is PostState.Error -> {
//                        Text(text = postState.message)
//                        Log.d("Error Tag", postState.message)
//                    }
//                    PostState.Loading -> Text(text = "loading...")
//                    is PostState.Success -> Text(text = postState.postList[0].body)
//                }
                
                Text(text = "${userList.size}")
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HilttutorialTheme {
        Greeting("Android")
    }
}