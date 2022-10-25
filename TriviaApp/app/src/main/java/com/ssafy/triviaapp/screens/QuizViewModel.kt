package com.ssafy.triviaapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.triviaapp.data.DataOrException
import com.ssafy.triviaapp.model.QuizListItem
import com.ssafy.triviaapp.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {
    val data: MutableState<DataOrException<ArrayList<QuizListItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getAllQuizList()
    }

    private fun getAllQuizList() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = quizRepository.getAllQuizList()
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }

        }
    }
}