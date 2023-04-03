package com.example.detaq

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.core_ui.LocalGradient
import com.example.core_ui.R
import com.example.detaq.components.AppBottomBar
import com.example.detaq.navigation.Route
import com.example.detaq.navigation.TopLevelDestination
import com.example.history_presenter.history.HistoryScreen
import com.example.home_presenter.first_aid.FirstAidScreen
import com.example.home_presenter.home.HomeScreen
import com.example.home_presenter.independent_handling.IndependentHandlingScreen
import com.example.home_presenter.notification.NotificationScreen
import com.example.landing_presenter.login.LoginScreen
import com.example.landing_presenter.onboarding.OnBoardingScreen
import com.example.landing_presenter.register.RegisterScreen
import com.example.landing_presenter.splash.SplashScreen
import com.example.profile_presenter.connect_family.ConnectFamilyScreen
import com.example.profile_presenter.connect_wristband.ConnectWristbandScreen
import com.example.profile_presenter.my_family.MyFamilyScreen
import com.example.profile_presenter.profile.ProfileScreen
import com.example.reminder_presenter.reminder.ReminderScreen
import com.example.sos_presenter.countdown.CountDownScreen
import com.example.sos_presenter.countdown_sent.CountDownSentScreen
import com.example.sos_presenter.sos.SosScreen
import com.example.sos_presenter.sos_map.SosMapScreen

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
                    showSnackBar = {
                        appState.showSnackBar(it)
                    },
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
                    showSnackBar = {
                        appState.showSnackBar(it)
                    },
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
                    showSnackBar = {
                        appState.showSnackBar(it)
                    },
                    onNotificationClick = {
                        navController.navigate(Route.Notification.name)
                    },
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
                        navController.navigate(Route.SosCountDownSent.name)
                    }
                )
            }

            composable(Route.SosCountDownSent.name) {
                CountDownSentScreen(
                    showSnackBar = {
                        appState.showSnackBar(it)
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = TopLevelDestination.Reminder.name,
                deepLinks = listOf(
                    navDeepLink {
                        action = Intent.ACTION_VIEW
                        uriPattern = "detaq://reminder"
                    }
                )
            ) {
                ReminderScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(TopLevelDestination.History.name) {
                HistoryScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(TopLevelDestination.Profile.name) {
                ProfileScreen(
                    onConnectClick = {
                        navController.navigate(Route.ConnectWithFamily.name)
                    },
                    onMyFamilyClick = {
                        navController.navigate(Route.MyFamily.name)
                    },
                    onConnectWristbandClick = {
                        navController.navigate(Route.ConnectWristband.name)
                    },
                    onLogOut = {
                        navController.navigate(Route.Login.name) {
                            popUpTo(Route.Splash.name) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Route.ConnectWithFamily.name) {
                ConnectFamilyScreen(
                    showSnackBar = {
                        appState.showSnackBar(it)
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.ConnectWristband.name) {
                ConnectWristbandScreen(
                    showSnackBar = {
                        appState.showSnackBar(it)
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.MyFamily.name) {
                MyFamilyScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.Notification.name,
                deepLinks = listOf(
                    navDeepLink {
                        action = Intent.ACTION_VIEW
                        uriPattern = "detaq://notification"
                    }
                )
            ) {
                NotificationScreen(
                    onReminderClick = {
                        navController.navigate(TopLevelDestination.Reminder.name)
                    },
                    onSosClick = { lat, long ->
                        navController.navigate(Route.SosMap.name + "/$lat/$long")
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.SosMap.name + "/{lat}/{long}",
                arguments = listOf(
                    navArgument("lat") {
                        type = NavType.StringType
                    },
                    navArgument("long") {
                        type = NavType.StringType
                    }
                )
            ) { navBackStackEntry ->
                val lat = navBackStackEntry.arguments?.getString("lat")?.toDoubleOrNull()
                val long = navBackStackEntry.arguments?.getString("long")?.toDoubleOrNull()

                SosMapScreen(
                    lat = lat,
                    long = long,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}