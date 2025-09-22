package com.rizzard23.kuizu.presentation.screens.tabbed

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizzesScreen(
    navController: NavController,
    counter : Int
) {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Home")},
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Home Route")
            Text("Counter: $counter")
            ElevatedButton(
                onClick = {
                }
            ) {
                Text("Go to Other Route")
            }
        }
    }
}