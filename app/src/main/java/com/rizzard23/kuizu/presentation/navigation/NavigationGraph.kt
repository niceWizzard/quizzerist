package com.rizzard23.kuizu.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rizzard23.kuizu.presentation.screens.QuizzesScreen
import com.rizzard23.kuizu.presentation.screens.SessionsScreen
import com.rizzard23.kuizu.presentation.screens.SettingsScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navController = navController
            )
        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = ScreenRoute.QuizRoute
            ) {
                composable<ScreenRoute.QuizRoute> {
                    QuizzesScreen(
                        navController = navController,
                    )
                }
                composable<ScreenRoute.SessionRoute> {
                    SessionsScreen(
                        navController = navController
                    )
                }
                composable<ScreenRoute.SettingsRoute> {
                    SettingsScreen(
                        navController = navController
                    )
                }
            }
        }

    }
}