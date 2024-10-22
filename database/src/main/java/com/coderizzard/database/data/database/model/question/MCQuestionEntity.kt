package com.coderizzard.database.data.database.model.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coderizzard.core.data.model.question.MCQuestion
import java.util.UUID

@Entity
data class MCQuestionEntity(
    @PrimaryKey
    override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo("text")
    override val text: String,
    @ColumnInfo("point")
    override val point: Int,
    @ColumnInfo("quiz_id")
    override val quizId: String,
    @ColumnInfo("options")
    val options : List<String>,
    @ColumnInfo("answer")
    val answer : List<Int>,
): QuestionEntity {
    fun toMCQuestion() : MCQuestion {
        return MCQuestion(
            id = this.id,
            text = this.text,
            point = this.point,
            answer = this.answer,
            options = this.options,
            quizId = this.quizId,
        )
    }
}