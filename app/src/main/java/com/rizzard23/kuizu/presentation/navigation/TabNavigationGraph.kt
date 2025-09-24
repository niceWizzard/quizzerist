package com.rizzard23.kuizu.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        NavHost(
            navController = navController,
            startDestination = TabRoute.QuizRoute,
            modifier = Modifier.weight(1f)
        ) {
            composable<TabRoute.QuizRoute> {
                QuizzesScreen(
                    navController = navController,
                    rootNavController = rootNavController,
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

        BottomNavBar(
            navController = navController
        )
    }
}