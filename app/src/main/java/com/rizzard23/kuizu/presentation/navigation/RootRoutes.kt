package com.rizzard23.kuizu.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.rizzard23.kuizu.ui.theme.MyIcons
import kotlinx.serialization.Serializable

sealed interface TabRoute  {
    companion object {
        val tabbedEntries = listOf<TabRoute>(
            QuizRoute,
            SessionRoute,
            SettingsRoute,
        )
    }
    val iconImage : ImageVector
    val routeLabel : String
    @Serializable
    object QuizRoute : TabRoute {
        override val iconImage: ImageVector = MyIcons.QuestionBox
        override val routeLabel: String = "Quizzes"
    }

    @Serializable
    object SessionRoute : TabRoute {
        override val iconImage: ImageVector = MyIcons.History
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

