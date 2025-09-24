package com.rizzard23.kuizu.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.Serializable

sealed interface TabRoute  {
    companion object {
        val tabbedEntries = listOf<TabRoute>(
            TabRoute.QuizRoute,
            TabRoute.SessionRoute,
            TabRoute.SettingsRoute,
        )
    }
    val iconImage : ImageVector
    val routeLabel : String
    @Serializable
    object QuizRoute : TabRoute {
        override val iconImage: ImageVector = Icons.Default.Home
        override val routeLabel: String = "Quizzes"
    }

    @Serializable
    object SessionRoute : TabRoute {
        override val iconImage: ImageVector = Icons.Default.FavoriteBorder
        override val routeLabel: String = "Sessions"
    }

    @Serializable
    object SettingsRoute : TabRoute {
        override val iconImage: ImageVector = Icons.Default.Settings
        override val routeLabel: String = "Settings"
    }
}

sealed interface RootRoutes  {
    @Serializable
    data class  QuizDetails(val id : String) : RootRoutes

    @Serializable
    object MainRoute : RootRoutes

    @Serializable
    object OtherRoute : RootRoutes


}

