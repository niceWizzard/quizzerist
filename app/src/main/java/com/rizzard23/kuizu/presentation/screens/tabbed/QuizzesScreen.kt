package com.rizzard23.kuizu.presentation.screens.tabbed

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rizzard23.kuizu.presentation.components.SortModalSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizzesScreen(
    navController: NavController,
    counter : Int
) {

    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if(isBottomSheetOpen)
        SortModalSheet(
            bottomSheetState = bottomSheetState,
            onDismissRequest = {
                scope.launch {
                    bottomSheetState.hide()
                    isBottomSheetOpen = false
                }
            }
        )

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
                    IconButton(
                        onClick = {
                            scope.launch {
                                isBottomSheetOpen = true
                                bottomSheetState.show()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
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

@Preview
@Composable
private fun QuizzesScreenPreview() {
    Surface {
        QuizzesScreen(
            navController = rememberNavController(),
            counter = 1,
        )
    }
}