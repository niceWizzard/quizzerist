package com.rizzard23.kuizu.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


sealed interface ScreenRoute  {
    val iconImage : ImageVector
    val routeLabel : String

    companion object {
        val entries = listOf<ScreenRoute>(
            QuizRoute,
            SessionRoute,
            SettingsRoute,
        )
    }


    @Serializable
    object QuizRoute : ScreenRoute {
        override val iconImage: ImageVector = Icons.Default.Home
        override val routeLabel: String = "Quizzes"
    }

    @Serializable
    object SessionRoute : ScreenRoute {
        override val iconImage: ImageVector = Icons.Default.FavoriteBorder
        override val routeLabel: String = "Sessions"
    }

    @Serializable
    object SettingsRoute : ScreenRoute {
        override val iconImage: ImageVector = Icons.Default.Settings
        override val routeLabel: String = "Settings"
    }


}

