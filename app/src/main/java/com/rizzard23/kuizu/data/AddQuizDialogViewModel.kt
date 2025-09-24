package com.rizzard23.kuizu.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizzard23.kuizu.data.repository.Quiz
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

sealed interface AddQuizDialogState {
    object Normal : AddQuizDialogState
    object Fetching : AddQuizDialogState
    object Parsing : AddQuizDialogState
}

class AddQuizDialogViewModel: ViewModel() {
    private val _quizUrlInput  = MutableStateFlow("")
    private val _state = MutableStateFlow<AddQuizDialogState>(AddQuizDialogState.Normal)

    private val _dialogProcessFinishEvent = MutableSharedFlow<Quiz>()

    val quizUrlInput = _quizUrlInput.asStateFlow()
    val dialogProcessFinishEvent = _dialogProcessFinishEvent.asSharedFlow()

    val state = _state.asStateFlow()

    val quizInputError = quizUrlInput.map {
        val input = it.trim()
        val pattern = Regex(
            """
            ^(https?://)?(www\.)?(wayground\.com|quizizz\.com)/(?:join/)?quiz/([a-f0-9]{24})(/\S*)?(\?\S*)?(#\S*)?$
            """.trimIndent())
            input.trim().isNotEmpty() && !pattern.matches(input)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false,
    )

    fun setQuizUrlValue(text : String ){
        _quizUrlInput.value = text
    }

    fun addQuiz(url : String) {
        viewModelScope.launch {
            _state.value = AddQuizDialogState.Fetching
            delay(500)
            _state.value = AddQuizDialogState.Parsing
            delay(500)
            _state.value = AddQuizDialogState.Normal
            _dialogProcessFinishEvent.emit(
                Quiz(
                    id = _quizUrlInput.value,
                    name = "Name: ${Random.nextBytes(10)}",
                    author = "Name: ${Random.nextBytes(13)}",
                    remoteId = "Remote Id: ${Random.nextBytes(15)}"
                )
            )
        }

    }


}