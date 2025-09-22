package com.rizzard23.kuizu.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rizzard23.kuizu.data.QuizzesViewModel
import com.rizzard23.kuizu.presentation.screens.tabbed.QuizzesScreen
import com.rizzard23.kuizu.presentation.screens.tabbed.SessionsScreen
import com.rizzard23.kuizu.presentation.screens.tabbed.SettingsScreen
import org.koin.androidx.compose.koinViewModel

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
                val quizzesViewModel : QuizzesViewModel = koinViewModel()
                val counter by quizzesViewModel.counter.collectAsState()
                QuizzesScreen(
                    navController = navController,
                    counter,
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