package com.rizzard23.kuizu.data

import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizzard23.kuizu.data.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuizzesViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {
    private val _isBottomSheetOpen = MutableStateFlow(false)
    private val _isAddDialogOpen = MutableStateFlow(false)

    val quizzesFlow = quizRepository.quizzesListFlow

    val isBottomSheetOpen = _isBottomSheetOpen.asStateFlow()
    val isAddDialogOpen = _isAddDialogOpen.asStateFlow()

    fun addQuiz() {
        viewModelScope.launch {
            quizRepository.addQuiz()
        }
    }

    fun setBottomSheetVisibility(value: Boolean) {
        _isBottomSheetOpen.value = value
    }

    fun setAddDialogVisibility(value : Boolean) {
        _isAddDialogOpen.value = value
    }


}