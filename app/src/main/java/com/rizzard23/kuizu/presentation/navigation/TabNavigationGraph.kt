package com.rizzard23.kuizu.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rizzard23.kuizu.presentation.screens.tabbed.QuizzesScreen
import com.rizzard23.kuizu.presentation.screens.tabbed.SessionsScreen
import com.rizzard23.kuizu.presentation.screens.tabbed.SettingsScreen

@Composable
fun TabNavigationGraph(
    rootNavController: NavController,
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        val a = innerPadding
        NavHost(
            navController = navController,
            startDestination = TabRoute.QuizRoute
        ) {
            composable<TabRoute.QuizRoute> {
                QuizzesScreen(
                    navController = navController,
                )
            }
            composable<TabRoute.SessionRoute> {
                SessionsScreen(
                    navController = navController
                )
            }
            composable<TabRoute.SettingsRoute> {
                SettingsScreen(
                    navController = navController,
                    onOtherRoute = {
                        rootNavController.navigate(RootRoutes.OtherRoute)
                    }
                )
            }
        }
    }
}