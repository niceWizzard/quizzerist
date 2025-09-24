package com.rizzard23.kuizu.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rizzard23.kuizu.data.AddQuizDialogState
import com.rizzard23.kuizu.data.AddQuizDialogViewModel
import com.rizzard23.kuizu.data.repository.Quiz
import com.rizzard23.kuizu.ui.theme.KuizuTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddQuizDialogContent(
    onDismissRequest: () -> Unit,
    onQuizAdd : (Quiz) -> Unit,
) {
    val addQuizDialogViewModel : AddQuizDialogViewModel = koinViewModel()
    val quizUrlInputValue by addQuizDialogViewModel.quizUrlInput.collectAsState()
    val quizUrlInputHasError by addQuizDialogViewModel.quizInputError.collectAsState()
    val clipboardManager = LocalClipboard.current
    val coroutineScope = rememberCoroutineScope()
    val state by addQuizDialogViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        addQuizDialogViewModel.dialogProcessFinishEvent.collect {
            onDismissRequest()
            onQuizAdd(it)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addQuizDialogViewModel.setQuizUrlValue("")
        }
    }

    Content(
        state = state,
        onDismissRequest = onDismissRequest,
        onTextInputChange = {
            addQuizDialogViewModel.setQuizUrlValue(it)
        },
        quizUrlInputValue = quizUrlInputValue,
        quizUrlInputHasError = quizUrlInputHasError,
        onAddButtonPress = {
            if(quizUrlInputValue.trim().isNotEmpty() &&
                !quizUrlInputHasError) {
                addQuizDialogViewModel.addQuiz(
                    quizUrlInputValue
                )
            }
        },
        onAccentButtonPress = {
            if(quizUrlInputValue.isEmpty()) {
                coroutineScope.launch {
                    clipboardManager.getClipEntry()?.let {
                        val lastIndex = it.clipData.itemCount - 1
                        if(lastIndex >= 0) {
                            addQuizDialogViewModel.setQuizUrlValue(
                                it.clipData.getItemAt(lastIndex).text.toString()
                            )
                        }
                    }
                }
            } else {
                addQuizDialogViewModel.setQuizUrlValue("")
            }
        }
    )
}

@Composable
private fun Content(
    state: AddQuizDialogState,
    onDismissRequest: () -> Unit,
    onTextInputChange: (String) -> Unit,
    quizUrlInputValue: String,
    quizUrlInputHasError: Boolean,
    onAccentButtonPress: () -> Unit,
    onAddButtonPress: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        when(state) {
            AddQuizDialogState.Normal -> {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        "Add New Quiz",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Example dialog content
                    OutlinedTextField(
                        value = quizUrlInputValue,
                        onValueChange = onTextInputChange,
                        label = { Text("Quiz Link") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                            .focusRequester(focusRequester),
                        isError = quizUrlInputHasError,
                    )

                    if(quizUrlInputHasError) {
                        Text(
                            text = "Please input a valid wayground link!",
                            color = MaterialTheme.colorScheme.error,
                        )
                    }

                    // Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        TextButton(onClick = onAccentButtonPress) {
                            Text(
                                text = if(quizUrlInputValue.isEmpty()) {
                                    "Paste"
                                } else {
                                    "Clear"
                                }
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = onAddButtonPress,
                            enabled = quizUrlInputValue.trim().isNotEmpty() && !quizUrlInputHasError
                        ) {
                            Text("Add")
                        }
                    }
                }
            }
            AddQuizDialogState.Fetching -> {
                Row(
                    modifier = Modifier.padding(24.dp)
                ) {
                    CircularProgressIndicator()
                    Text("Fetching")
                }
            }
            AddQuizDialogState.Parsing -> {
                Row(
                    modifier = Modifier.padding(24.dp)
                ) {
                    CircularProgressIndicator()
                    Text("Parsing")
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AddQuizDialogPreview() {
    KuizuTheme { // Make sure you have a theme wrapper
        Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
            // The dialog needs a background to be visible in preview
            Content(
                state = AddQuizDialogState.Normal,
                onDismissRequest = {},
                onTextInputChange = {},
                quizUrlInputValue = "Wow",
                quizUrlInputHasError = true,
                onAddButtonPress = {},
                onAccentButtonPress = {}
            )
        }
    }
}