package com.example.detaq

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.detaq.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope
) {
    fun showSnackBar(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        @Composable get() = when (currentDestination?.route) {
            TopLevelDestination.Home.name -> true
            TopLevelDestination.History.name -> true
            TopLevelDestination.Reminder.name -> true
            TopLevelDestination.Profile.name -> true
            else -> false
        }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState = remember(scaffoldState, navController) {
    AppState(
        scaffoldState,
        navController,
        coroutineScope
    )
}