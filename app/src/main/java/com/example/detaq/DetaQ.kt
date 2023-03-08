package com.example.detaq

import android.window.SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.detaq.navigation.Route
import com.example.landing_presenter.login.LoginScreen
import com.example.landing_presenter.onboarding.OnBoardingScreen
import com.example.landing_presenter.splash.SplashScreen

@Composable
fun DetaQ(
    appState: AppState = rememberAppState(),
    shouldShowOnBoarding: Boolean = true
) {
    val scaffoldState = appState.scaffoldState
    val navController = appState.navController

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = Route.Splash.name
        ) {
            composable(Route.Splash.name) {
                SplashScreen(
                    onFinish = {
                        if (shouldShowOnBoarding) {
                            navController.navigate(Route.OnBoarding.name)
                        } else {
                            navController.navigate(Route.Home.name)
                        }
                    }
                )
            }

            composable(Route.OnBoarding.name) {
                OnBoardingScreen {
                    navController.navigate(Route.Login.name)
                }
            }

            composable(Route.Login.name) {
                LoginScreen(
                    onForgotPassword = {

                    },
                    onSignUp = {
                        navController.navigate(Route.Register.name)
                    },
                    onLogin = {
                        navController.navigate(Route.Home.name)
                    }
                )
            }
            
            composable(Route.Register.name) {
                Text(text = "Register")
            }
        }
    }
}