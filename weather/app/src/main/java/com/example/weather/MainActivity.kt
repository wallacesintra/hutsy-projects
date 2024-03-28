package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.weather.presentation.design_system.theme.WeatherTheme
import com.example.weather.presentation.navigation.NavigationHost
import com.example.weather.presentation.screens.CurrentScreen
import com.example.weather.presentation.screens.ForecastScreen
import com.example.weather.presentation.screens.TestContainer
import com.example.weather.presentation.viewmodel.WeatherViewModel
import com.example.weather.presentation.viewmodel.WeatherViewModelFactory

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
//                    val navController = rememberNavController()
//                    Scaffold(
//                        topBar = {
//                                 TitleBar()
//                        },
//                        bottomBar = {
//                            BottomNavigation(
//                                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
//                            ) {
//                                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                                val currentDestination = navBackStackEntry?.destination
//
//                                items.forEach { screen ->
//                                    BottomNavigationItem(
//                                        selected = currentDestination?.hierarchy?.any {it.route == screen.route} == true,
//                                        onClick = {
//                                                  navController.navigate(screen.route){
//                                                      popUpTo(navController.graph.findStartDestination().id){
//                                                          saveState = true
//                                                      }
//                                                      launchSingleTop = true
//                                                      restoreState = true
//                                                  }
//                                        },
//                                        icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = null )})
//                                }
//                            }
//                        }
//
//                    ) {paddingValues ->
//                        NavHost(
//                            navController = navController,
//                            startDestination = Screen.Current.route,
//                            modifier = Modifier.padding(paddingValues)
//                            ) {
//                            composable(Screen.Current.route) { CurrentContainer() }
//                            composable(Screen.Forecast.route) { ForecastContainer() }
//                        }
//                    }
//                    NavigationHost()
                    TestContainer()

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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        Greeting("Android")
    }
}