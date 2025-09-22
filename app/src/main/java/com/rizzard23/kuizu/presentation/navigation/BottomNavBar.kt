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
    val entries = ScreenRoute.entries
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsState(null)

    backStackEntry?.let { backStackEntry ->
        NavigationBar {
            entries.forEach { route ->
                val isSelected = backStackEntry.destination.hasRoute(route::class)
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if(!isSelected)
                            navController.navigate(route)
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