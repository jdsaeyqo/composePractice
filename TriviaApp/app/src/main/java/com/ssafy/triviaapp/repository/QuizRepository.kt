package com.ssafy.triviaapp.repository

import android.util.Log
import com.ssafy.triviaapp.data.DataOrException
import com.ssafy.triviaapp.model.QuizListItem
import com.ssafy.triviaapp.network.QuizApi
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor(private val quizApi: QuizApi) {
    private val dataOrException = DataOrException<ArrayList<QuizListItem>, Boolean, Exception>()

    suspend fun getAllQuizList(): DataOrException<ArrayList<QuizListItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = quizApi.getAllQuiz()

            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }

        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d(
                "TAG",
                "QuizRepositoryException : getAllQuizList - ${dataOrException.e!!.localizedMessage} "
            )

        }

        return dataOrException
    }
}