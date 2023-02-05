package com.example.hewa

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.hewa.ui.results.ResultsViewModel
import com.example.hewa.ui.WeatherAppNavigation
import com.example.hewa.ui.components.WeatherBackground
import com.example.hewa.ui.home.BottomNavBar
import com.example.hewa.ui.home.BottomNavScreen
import com.example.hewa.ui.home.HomeViewModel
import com.example.hewa.ui.splashScreen.WeatherSplashScreen
import com.example.hewa.ui.theme.HewaTheme
import com.example.hewa.utils.SplashScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HewaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WeatherBackground {
                        WeatherApp {
                            finish()
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun WeatherApp(finish: () -> Unit) {
    val transitionState = remember {
        MutableTransitionState(SplashScreenState.Show)
    }
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val results = viewModel(modelClass = ResultsViewModel::class.java)
    val state = homeViewModel.state

    /*
    *show the splash screen when the app launches
    */
    AnimatedVisibility(
        visible = transitionState.targetState == SplashScreenState.Show,
        exit = slideOutHorizontally(
            targetOffsetX = { -300 },
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        ),
    ) {
        WeatherSplashScreen {
            transitionState.targetState = SplashScreenState.Completed
        }
    }
    /*
    *Slide in the Home screen after the splash screen is shown
    */
    AnimatedVisibility(
        visible = transitionState.targetState == SplashScreenState.Completed,
        enter = slideInHorizontally(
            initialOffsetX = { -300 },
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    ) {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    selectedScreen = state.selectedScreen,
                    onSelected = {
                        homeViewModel.onScreenChange(it)
                        if (it != BottomNavScreen.Back) {
                            navController.navigate(it.name) {
                                launchSingleTop = true
                                popUpTo(BottomNavScreen.Home.name)
                            }
                        } else {
                            finish.invoke()
                        }
                    },
                )
            }
        ) {
            WeatherAppNavigation(
                navHostController = navController,
                viewModel = homeViewModel,
                modifier = Modifier.padding(it),
                resultsViewModel = results
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    HewaTheme {
        WeatherApp {

        }
    }
}