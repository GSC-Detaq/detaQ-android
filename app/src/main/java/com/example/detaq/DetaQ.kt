package com.example.detaq

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core_ui.LocalGradient
import com.example.core_ui.R
import com.example.detaq.components.AppBottomBar
import com.example.detaq.navigation.Route
import com.example.detaq.navigation.TopLevelDestination
import com.example.home_presenter.first_aid.FirstAidScreen
import com.example.home_presenter.home.HomeScreen
import com.example.home_presenter.independent_handling.IndependentHandlingScreen
import com.example.landing_presenter.login.LoginScreen
import com.example.landing_presenter.onboarding.OnBoardingScreen
import com.example.landing_presenter.register.RegisterScreen
import com.example.landing_presenter.splash.SplashScreen
import com.example.sos_presenter.countdown.CountDownScreen
import com.example.sos_presenter.sos.SosScreen
import timber.log.Timber

@Composable
fun DetaQ(
    appState: AppState = rememberAppState(),
    shouldShowOnBoarding: Boolean = true
) {
    val localGradient = LocalGradient.current
    val scaffoldState = appState.scaffoldState
    val navController = appState.navController

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                AppBottomBar(
                    currentDestination = appState.currentDestination,
                    onTabSelected = {
                        navController.navigate(it.name)
                    }
                )
            }
        },
        floatingActionButton = {
            if (appState.shouldShowBottomBar) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Route.Sos.name)
                    },
                    modifier = Modifier
                        .size(56.dp)
                        .offset(y = (56 / 4).dp)
                        .background(
                            localGradient.primary,
                            CircleShape
                        ),
                    backgroundColor = Color.Transparent,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sos_icon),
                        contentDescription = "SOS",
                        modifier = Modifier
                            .size(27.dp)
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
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
                            navController.navigate(TopLevelDestination.Home.name)
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
                        navController.navigate(TopLevelDestination.Home.name)
                    }
                )
            }
            
            composable(Route.Register.name) {
                RegisterScreen(
                    onSignIn = {
                        navController.navigateUp()
                    },
                    onConfirmClick = {
                        navController.navigate(TopLevelDestination.Home.name)
                    }
                )
            }

            composable(TopLevelDestination.Home.name) {
                HomeScreen(
                    onFirstAidClick = {
                        navController.navigate(Route.FirstAid.name)
                    },
                    onAloneClick = {
                        navController.navigate(Route.IndependentHandling.name)
                    }
                )
            }

            composable(Route.FirstAid.name) {
                FirstAidScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.IndependentHandling.name) {
                IndependentHandlingScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.Sos.name) {
                SosScreen(
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onSosClick = {
                        navController.navigate(Route.SosCountDown.name)
                    }
                )
            }

            composable(Route.SosCountDown.name) {
                CountDownScreen(
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onCountDownFinish = {
                        Timber.d("COUNT DOWN FINISH!")
                    }
                )
            }
        }
    }
}