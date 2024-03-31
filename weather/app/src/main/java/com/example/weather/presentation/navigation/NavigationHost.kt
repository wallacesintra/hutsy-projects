package com.example.weather.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weather.presentation.components.TitleBar
import com.example.weather.presentation.models.CityState
import com.example.weather.presentation.models.CityUiState
import com.example.weather.presentation.models.WeatherState
import com.example.weather.presentation.screens.CityScreen
import com.example.weather.presentation.screens.CurrentScreen
import com.example.weather.presentation.screens.ErrorScreen
import com.example.weather.presentation.screens.ForecastScreen
import com.example.weather.presentation.screens.LoadingScreen
import com.example.weather.presentation.viewmodels.CityViewModel
import com.example.weather.presentation.viewmodels.OpenWeatherViewModel

@Composable
fun NavigationHost(){
    val weatherViewModel: OpenWeatherViewModel = viewModel(factory = OpenWeatherViewModel.Factory)
    val uiState = weatherViewModel.weatherState

    val cityViewModel: CityViewModel = viewModel(factory = CityViewModel.Factory)
    val cityUiState = cityViewModel.cityState
    var isCityScreen = cityViewModel.isCityScreen

    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TitleBar()
        },

        floatingActionButton = {
            if (isCityScreen){
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add location check its weather"
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
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
                        icon = { Icon(painter = painterResource(id = screen.icon!!), contentDescription = null ) })
                }
            }
        }

    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Current.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Current.route) { CurrentContainer(uiState) }
            composable(Screen.Forecast.route) { ForecastContainer(uiState) }
            composable(Screen.City.route) {
                CityContainer(uiState = cityUiState)
            }
            composable(Screen.Loading.route){ LoadingScreen()}
            composable(Screen.Error.route) { ErrorScreen()}
        }
    }
}

@Composable
fun CurrentContainer(
    uiState: WeatherState
){
    when(uiState){
        is WeatherState.Loading -> LoadingScreen()
        is WeatherState.Success -> CurrentScreen(state = uiState.weatherUiState)
        else -> {ErrorScreen()}
    }
}



@Composable
fun ForecastContainer(
    uiState: WeatherState
){
    when(uiState){
        is WeatherState.Loading -> LoadingScreen()
        is WeatherState.Success -> ForecastScreen(list = uiState.weatherUiState.hourlyForecast)
        else -> {ErrorScreen()}
    }
}

@Composable
fun CityContainer(
    uiState: CityState
){
    when(uiState){
        is CityState.Loading -> LoadingScreen()
        is CityState.Success -> CityScreen(state = uiState.cityUiState)
        else -> ErrorScreen()
    }

}