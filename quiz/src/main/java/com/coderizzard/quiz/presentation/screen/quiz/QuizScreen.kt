package com.coderizzard.quiz.presentation.screen.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.coderizzard.core.data.model.Quiz
import com.coderizzard.core.data.model.question.IdentificationQuestion
import com.coderizzard.core.data.navigation.RootRoute
import java.time.LocalDateTime

@Composable
fun QuizScreen(
    navController: NavController
) {
    val quizRoute = navController.currentBackStackEntry?.toRoute<RootRoute.Quiz>()
        ?: throw Exception("Reached Quiz(#id) without a quiz route")
    val viewModel : QuizScreenViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.initialize(quizRoute.id)
    }
    val quiz by viewModel.quizState.collectAsState()
    when(quizRoute.id.trim()) {
        "" -> {
            Text("Invalid quiz id given.")
        }
        else -> {
            when(val state = quiz) {
                is QuizUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize() ,
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
                is QuizUiState.Error -> {
                    Text(state.msg)
                }

                is QuizUiState.Success -> {
                    QuizScreenContent(state.quiz)
                }
            }
        }
    }


}

@Composable
private fun QuizScreenContent(quiz: Quiz) {
    Surface(
        modifier =  Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(12.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                quiz.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    quiz.author,
                    fontSize = 12.sp,
                )
                Text(
                    "Created at: ${quiz.createdAt}",
                    fontSize = 12.sp,
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            if(quiz.questions.isEmpty()) {
                Text(
                    "Empty quiz...",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            quiz.questions.map {q ->
                QuestionComp(q)
            }
        }
    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    QuizScreenContent(
        Quiz(
            id = "lakjsdf",
            name = "Some Example quiz",
            author = "Example author",
            createdAt = LocalDateTime.now(),
            questions = listOf(
                IdentificationQuestion(
                    text = "lajksdf",
                    quizId = "lajksdf",
                    point = 1,
                    id = "klasfjdadsfiow",
                    answer = "lkajsdf"
                )
            )
        )
    )
}