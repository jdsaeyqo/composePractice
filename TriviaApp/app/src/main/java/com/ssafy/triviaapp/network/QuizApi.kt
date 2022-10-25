package com.ssafy.triviaapp.network

import com.ssafy.triviaapp.model.QuizList
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuizApi {

    @GET("world.json")
    suspend fun getAllQuiz() : QuizList
}