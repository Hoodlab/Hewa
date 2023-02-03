package com.example.hewa.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hewa.ui.results.ResultScreen
import com.example.hewa.ui.results.ResultsViewModel
import com.example.hewa.ui.home.*
import com.example.hewa.ui.search.SearchScreen

@Composable
fun WeatherAppNavigation(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    resultsViewModel: ResultsViewModel,
    modifier: Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavScreen.Home.name
    ) {
        composable(route = BottomNavScreen.Home.name) {
            HomeScreen(homeViewModel = viewModel, modifier)
        }
        composable(route = BottomNavScreen.Search.name) {
            SearchScreen {
                viewModel.onScreenChange(BottomNavScreen.Result)
                resultsViewModel.searchWeatherInfo(it)
                navHostController.navigate("Result"){
                    launchSingleTop = true
                    popUpTo(BottomNavScreen.Home.name)
                }
            }
        }
        composable(route = "Result") {
            ResultScreen(state = resultsViewModel.state, modifier = modifier)
        }
    }
}