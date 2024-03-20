package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weather.components.TitleBar
import com.example.weather.screens.CurrentScreen
import com.example.weather.screens.ForecastScreen
import com.example.weather.screens.Screen
import com.example.weather.screens.items
import com.example.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                                 TitleBar()
                        },
                        bottomBar = {
                            BottomNavigation(
                                backgroundColor = MaterialTheme.colorScheme.secondaryContainer
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                
                                items.forEach {screen ->
                                    BottomNavigationItem(
                                        selected = currentDestination?.hierarchy?.any {it.route == screen.route} == true,
                                        onClick = {
                                                  navController.navigate(screen.route){
                                                      popUpTo(navController.graph.findStartDestination().id){
                                                          saveState = true
                                                      }
                                                      launchSingleTop = true
                                                      restoreState = true
                                                  }
                                        },
                                        icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = null )})
                                }
                            }
                        }

                    ) {paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Current.route,
                            modifier = Modifier.padding(paddingValues)
                            ) {
                            composable(Screen.Current.route) { CurrentContainer() }
                            composable(Screen.Forecast.route) { ForecastContainer() }
                        }
                    }

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
fun CurrentContainer(){
    val context = LocalContext.current
    val viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(context)
    )
    val state by viewModel.uiState.collectAsState()
    CurrentScreen(state = state)
}

@Composable
fun ForecastContainer(){
    val context = LocalContext.current
    val viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(context)
    )
    val state by viewModel.uiState.collectAsState()
    ForecastScreen(list = state.hourlyForecast)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        Greeting("Android")
    }
}