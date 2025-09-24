package com.rizzard23.kuizu.presentation.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavigationGraph() {
    val navController = rememberNavController()
    Surface {
        NavHost(
            navController = navController,
            startDestination = RootRoutes.MainRoute,
            modifier = Modifier.fillMaxSize(1f),
        ) {
            composable<RootRoutes.MainRoute> {
                TabNavigationGraph(
                    rootNavController = navController,
                )
            }
            composable<RootRoutes.OtherRoute>(
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it /2 },
                        animationSpec = tween(100)
                    ) + fadeIn(animationSpec = tween(100))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it /2 },
                        animationSpec = tween(100)
                    ) + fadeOut(animationSpec = tween(100))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -it /2 },
                        animationSpec = tween(100)
                    ) + fadeIn(animationSpec = tween(100))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = {it /2 },
                        animationSpec = tween(100)
                    ) + fadeOut(animationSpec = tween(100))
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Other Route")
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text("Other Screen")
                    }
                }
            }

            composable<RootRoutes.QuizDetails>(

            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text("Quiz Details Screen")
                    }
                }
            }

        }

    }
}