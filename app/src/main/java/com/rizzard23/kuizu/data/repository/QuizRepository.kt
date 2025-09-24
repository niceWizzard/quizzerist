package com.rizzard23.kuizu.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class Quiz(
    val id : Int,
    val name : String,
)

class QuizRepository {

    private val _quizzesListFlow = MutableStateFlow<List<Quiz>>(emptyList())

    val quizzesListFlow = _quizzesListFlow.asStateFlow()


    suspend fun addQuiz() {
        _quizzesListFlow.value += Quiz(
            _quizzesListFlow.value.size,
            Random.nextBytes(100).toString()
        )
    }


}