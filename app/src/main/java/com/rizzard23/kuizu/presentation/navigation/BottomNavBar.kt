package com.rizzard23.kuizu.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute

@Composable
fun BottomNavBar(
    navController: NavController
) {
    val entries = TabRoute.tabbedEntries
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsState(null)

    backStackEntry?.let { backStackEntry ->
        NavigationBar {
            entries.forEachIndexed { index,route ->
                val isSelected = backStackEntry.destination.hasRoute(route::class)
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if(index == 0) {
                            navController.popBackStack(TabRoute.QuizRoute, true)
                            navController.navigate(route)
                        } else if (!isSelected) {
                            navController.popBackStack(TabRoute.QuizRoute, false)
                            navController.navigate(route)
                        }
                    },
                    icon = {
                        Icon(route.iconImage, contentDescription = "Localized description")
                    },
                    label = {
                        Text(route.routeLabel)
                    }
                )
            }

        }
    }
}