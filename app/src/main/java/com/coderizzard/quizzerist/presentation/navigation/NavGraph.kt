package com.coderizzard.quizzerist.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.coderizzard.quizzerist.presentation.screens.homescreen.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = RootNav.Home,
        enterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 200))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 200))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 200))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 200))
        }

    ) {
        homeNavGraph(navController)
        composable<RootNav.QuizSession> {
            val route = navController.currentBackStackEntry?.toRoute<RootNav.QuizSession>()
            Text("Quiz Session $route")
        }

    }
}

private fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation<RootNav.Home>(
        startDestination = HomeRoute.Quiz
    ) {
        composable<HomeRoute.Quiz>(
        ) {
            HomeScreen(
                navController = navController,
            )
        }
        composable<HomeRoute.Settings> {
            Text("SEttings ")
        }
        composable<HomeRoute.Sessions> {
            Column {
                Text("SESSIONS")
                ElevatedButton(
                    onClick = {
                        navController.navigate(
                            RootNav.QuizSession(id = "asdfasdfasdf!")
                        )
                    }
                ) {
                    Text("Start Session")
                }
            }
        }
    }
}




