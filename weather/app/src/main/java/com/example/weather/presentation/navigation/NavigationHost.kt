package com.example.weather.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.weather.presentation.models.WeatherCurrentState
import com.example.weather.presentation.screens.search.CityScreen
import com.example.weather.presentation.screens.currentLocation.CurrentScreen
import com.example.weather.presentation.screens.ErrorScreen
import com.example.weather.presentation.screens.forecast.ForecastScreen
import com.example.weather.presentation.screens.LoadingScreen
import com.example.weather.presentation.screens.NetworkErrorScreen
import com.example.weather.presentation.viewmodels.LocationsViewModel
import com.example.weather.presentation.viewmodels.CurrentLocationWeatherViewModel

@Composable
fun NavigationHost(
){
    val weatherViewModel: CurrentLocationWeatherViewModel = viewModel(factory = CurrentLocationWeatherViewModel.Factory)
    val uiState = weatherViewModel.weatherCurrentState

    val locationsViewModel: LocationsViewModel = viewModel(factory = LocationsViewModel.Factory)
    val cityUiState = locationsViewModel.cityState
    val userLocation = locationsViewModel.userLocation
    val state by locationsViewModel.state.collectAsState()

    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TitleBar()
        },

        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Screens.forEach { screen ->
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
                CityScreen(
                    cityState = cityUiState,
                    location = userLocation,
                    cityUiState = state,
                    onLocationChange = { locationsViewModel.onLocationChange(it) } ,
                    onDismiss = {locationsViewModel.toSuccess()},
                    onSearch = {locationsViewModel.searchLocation()},
                    onClick = {locationsViewModel.deleteLocation(it)},
                )
            }
            composable(Screen.Loading.route){ LoadingScreen()}
            composable(Screen.Error.route) { ErrorScreen()}
        }
    }
}

@Composable
fun CurrentContainer(
    uiState: WeatherCurrentState
){
    when(uiState){
        is WeatherCurrentState.Loading -> LoadingScreen()
        is WeatherCurrentState.Success -> CurrentScreen(state = uiState.weatherUiState)
        else -> {ErrorScreen()}
    }
}


@Composable
fun ForecastContainer(
    uiState: WeatherCurrentState
){
    when(uiState){
        is WeatherCurrentState.Loading -> LoadingScreen()
        is WeatherCurrentState.Success -> ForecastScreen(list = uiState.weatherUiState.hourlyForecast)
        is WeatherCurrentState.NetworkError -> NetworkErrorScreen()
        else -> {ErrorScreen()}
    }
}

