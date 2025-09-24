package com.rizzard23.kuizu.presentation.screens.tabbed

import android.annotation.SuppressLint
import android.os.SystemClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rizzard23.kuizu.data.QuizzesViewModel
import com.rizzard23.kuizu.data.repository.Quiz
import com.rizzard23.kuizu.presentation.components.AddQuizDialogContent
import com.rizzard23.kuizu.presentation.components.SortModalSheet
import com.rizzard23.kuizu.presentation.navigation.RootRoutes
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

sealed interface QuizzesScreenEvent {
    object FabButtonPressed : QuizzesScreenEvent
    data class BottomSheetToggle(val value : Boolean) : QuizzesScreenEvent
    data class AddDialogToggle(val value : Boolean) : QuizzesScreenEvent

    data class NavigateToQuiz(val id : String) : QuizzesScreenEvent
}

@Composable
inline fun debounced(crossinline onClick: () -> Unit, debounceTime: Long = 1000L): () -> Unit {
    var lastTimeClicked by remember { mutableLongStateOf(0L) }
    val onClickLambda: () -> Unit = {
        val now = SystemClock.uptimeMillis()
        if (now - lastTimeClicked > debounceTime) {
            onClick()
        }
        lastTimeClicked = now
    }
    return onClickLambda
}

@Composable
fun QuizzesScreen(
    navController: NavController,
    rootNavController: NavController,
) {
    val quizzesViewModel : QuizzesViewModel = koinViewModel()
    val quizzes by quizzesViewModel.quizzesFlow.collectAsState()
    val isBottomSheetOpen by quizzesViewModel.isBottomSheetOpen.collectAsState()
    val isAddDialogOpen by quizzesViewModel.isAddDialogOpen.collectAsState()
    Content(
        navController = navController,
        quizzes = quizzes,
        isBottomSheetOpen = isBottomSheetOpen,
        isAddDialogOpen = isAddDialogOpen,
        onEvent = { event ->
            when(event) {
                QuizzesScreenEvent.FabButtonPressed -> {
                    quizzesViewModel.setAddDialogVisibility(true)
                }

                is QuizzesScreenEvent.BottomSheetToggle -> {
                    quizzesViewModel.setBottomSheetVisibility(event.value)
                }
                is QuizzesScreenEvent.AddDialogToggle -> {
                    quizzesViewModel.setAddDialogVisibility(event.value)
                }
                is QuizzesScreenEvent.NavigateToQuiz -> {
                    quizzesViewModel.debouncedNavigate {
                        rootNavController.navigate(
                            RootRoutes.QuizDetails(event.id)
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    navController: NavController,
    quizzes: List<Quiz>,
    onEvent: (QuizzesScreenEvent) -> Unit,
    isBottomSheetOpen: Boolean,
    isAddDialogOpen: Boolean
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if(isBottomSheetOpen)
        SortModalSheet(
            bottomSheetState = bottomSheetState,
            onDismissRequest = {
                scope.launch {
                    bottomSheetState.hide()
                    onEvent(QuizzesScreenEvent.BottomSheetToggle(false))
                }
            }
        )

    if(isAddDialogOpen) {
        Dialog(
            onDismissRequest = {
                onEvent(QuizzesScreenEvent.AddDialogToggle(false))
            }
        ) {
            AddQuizDialogContent(
                onDismissRequest = {
                    onEvent(QuizzesScreenEvent.AddDialogToggle(false))
                },
                onQuizAdd = {
                    onEvent(
                        QuizzesScreenEvent.NavigateToQuiz(it.id)
                    )
                }
            )
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(NavigationBarDefaults.windowInsets),
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
                                onEvent(QuizzesScreenEvent.BottomSheetToggle(true))
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(QuizzesScreenEvent.FabButtonPressed)
                }
            ) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding) // or whatever padding you need
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    if(quizzes.isEmpty()) {
                        Text("No quizzes yet.")
                    }
                }
                items(quizzes) { quiz ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onEvent(QuizzesScreenEvent.NavigateToQuiz(quiz.id))
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                12.dp, 8.dp
                            )
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.Start)
                            ) {
                                Column {
                                    Text(
                                        quiz.name,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        quiz.author, fontWeight = FontWeight.Light, fontSize = 12.sp,
                                    )
                                }
                            }
                            Spacer(Modifier.height(12.dp))
                            HorizontalDivider(modifier = Modifier.height(12.dp))
                        }
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
private fun QuizzesScreenPreview() {
    Surface {
        Content(
            navController = rememberNavController(),
            quizzes = (1  .. 15).map {
                Quiz(
                    id = it.toString(),
                    name = Random.nextBytes(10).toString(),
                    author = Random.nextBytes(12).toString(),
                    remoteId = Random.nextBytes(15).toString(),
                )
            },
            onEvent = {},
            isBottomSheetOpen = false,
            isAddDialogOpen = false,
        )
    }
}