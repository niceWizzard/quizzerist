package com.coderizzard.quizzerist.presentation.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.coderizzard.core.data.navigation.HomeRoute
import com.coderizzard.core.data.navigation.RootRoute
import com.coderizzard.quiz.presentation.screen.quiz_list.QuizListScreenAppBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeAppBar(
    route: HomeRoute,
    navController: NavController
) {
    when(route) {
        else -> {
            TopAppBar(
                title = { Text(route.displayName) },
            )
        }
    }
}