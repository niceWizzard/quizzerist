package com.rizzard23.kuizu.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizzard23.kuizu.data.repository.QuizRepository
import kotlinx.coroutines.launch

class QuizzesViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {
    fun addQuiz() {
        viewModelScope.launch {
            quizRepository.addQuiz()
        }
    }

    val quizzesFlow = quizRepository.quizzesListFlow

}