package com.ssafy.triviaapp.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ssafy.triviaapp.component.Quizzes

@Composable
fun TriviaHome(quizViewModel: QuizViewModel = hiltViewModel()) {
    Quizzes(quizViewModel)
}