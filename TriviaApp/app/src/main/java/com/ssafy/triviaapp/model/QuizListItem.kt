package com.ssafy.triviaapp.model

data class QuizListItem(
    val answer: String,
    val category: String,
    val choices: List<String>,
    val question: String
)