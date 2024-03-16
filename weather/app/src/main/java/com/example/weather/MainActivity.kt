package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.components.HourlyForecast
import com.example.weather.data.ReadJSON
import com.example.weather.data.WeatherData
import com.example.weather.screens.CurrentScreen
import com.example.weather.ui.theme.WeatherTheme
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
//    private val viewModel by viewModels<WeatherViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
//        val jsonString = ReadJSON(baseContext, "data.json")
//        val gson = GsonBuilder()
//            .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
//                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                return@JsonDeserializer sdf.parse(json.asString)
//            })
//            .create()
//
//        val data = gson.fromJson(jsonString, WeatherData::class.java)

        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val uiState by viewModel.uiState.collectAsState()
//                    Greeting(uiState.place)
                    Container()
//                    CurrentScreen(state = uiState)
//                    if (data != null) {
////                        Text(text = data.list[0].main.feels_like.toString())
//                        HourlyForecast(list = data.list)
//                    } else {
//                        Text(text = "Data is null")
//                   }

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
fun Container(
    weatherViewModel: WeatherViewModel = viewModel()
){
    val context = LocalContext.current
    val viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(context)
    )
//    val uiState by weatherViewModel.uiState.collectAsState()
    val state by viewModel.uiState.collectAsState()
    CurrentScreen(state = state)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        Greeting("Android")
    }
}