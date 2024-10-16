package com.coderizzard.quizzerist.presentation.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coderizzard.core.data.model.question.IdentificationQuestion
import com.coderizzard.core.data.model.question.MultipleChoiceQuestion
import com.coderizzard.core.data.model.question.Question

@Composable
internal fun Question(q: Question) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(q.text)
        Spacer(Modifier.height(6.dp))
        when (q) {
            is IdentificationQuestion -> {
                Text("Answer: ${q.answer}")
            }

            is MultipleChoiceQuestion -> {
                q.options.mapIndexed { i, it ->
                    Text(
                        buildString {
                            if (q.answer.contains(i))
                                append("Correct - ")
                            append(it)
                        }
                    )
                }
            }
        }
    }
}