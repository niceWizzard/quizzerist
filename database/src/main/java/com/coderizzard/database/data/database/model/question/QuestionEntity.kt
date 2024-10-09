package com.coderizzard.database.data.database.model.question


sealed interface QuestionEntity {
    val id : String
    val text : String
    val point : Int
    val quizId : String
}