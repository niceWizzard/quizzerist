package com.rizzard23.kuizu.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import kotlin.random.Random

data class Quiz(
    val id : String,
    val name : String,
    val author : String,
    val createdAt : LocalDateTime = LocalDateTime.now(),
    val imageLink : String = "",
    val remoteId : String,
    val localImagePath : String = "",
) {
}

class QuizRepository {

    private val _quizzesListFlow = MutableStateFlow<List<Quiz>>(emptyList())

    val quizzesListFlow = _quizzesListFlow.asStateFlow()


    suspend fun addQuiz() {
        _quizzesListFlow.value += Quiz(
            _quizzesListFlow.value.size.toString(),
            Random.nextBytes(100).toString(),
            author = "WOW ${Random.nextBytes(10)}",
            remoteId = Random.nextBytes(10).toString(),
        )
    }


}