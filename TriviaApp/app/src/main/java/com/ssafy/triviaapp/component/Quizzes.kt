package com.ssafy.triviaapp.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.triviaapp.model.QuizListItem
import com.ssafy.triviaapp.screens.QuizViewModel
import com.ssafy.triviaapp.util.AppColors
import java.lang.Exception

@Composable
fun Quizzes(quizViewModel: QuizViewModel) {

    val quizzes = quizViewModel.data.value.data?.toMutableList()

    val questionIndex = remember {
        mutableStateOf(0)
    }

    if (quizViewModel.data.value.loading == true) {
        CircularProgressIndicator()
        Log.d("Loading", "Quizzes: Loading")
    } else {
        val quiz = try {
            quizzes?.get(questionIndex.value)
        } catch (e: Exception) {
            null
        }

        Log.d("Quizzes", "Quizzes: ${questionIndex.value}")
        Log.d("Quizzes", "Quizzes: $quiz")
        if (quizzes != null) {
            QuizDisplay(
                question = quiz!!,
                questionIndex = questionIndex,
                quizzes.size,
                viewModel = quizViewModel
            ) {
                questionIndex.value = questionIndex.value + 1
            }
        }
    }
    Log.d("size", "Quizzes: ${quizzes?.size}")
}

//@Preview
@Composable
fun QuizDisplay(
    question: QuizListItem,
    questionIndex: MutableState<Int>,
    outOf: Int,
    viewModel: QuizViewModel,
    onNextClicked: (Int) -> Unit
) {


    val choicesState by remember(question) {
        mutableStateOf(question.choices.toMutableList())
    }
    Log.d("Quizzes", "QuizDisplay: ${question}")
    Log.d("Quizzes", "QuizDisplay: ${choicesState}")
    var answerState by remember(question) {
        mutableStateOf<Int?>(null)
    }

    var correctAnswerState by remember(question) {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState = it
            correctAnswerState = choicesState[it] == question.answer
        }
    }


    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), color = AppColors.mDarkPurple
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            if (questionIndex.value >= 3) {
                ShowProgress(score = questionIndex.value)
            }
            QuizTracker(counter = questionIndex.value + 1, outOf)
            DrawDottedLine(pathEffect)

            Column {
                Text(
                    text = question.question,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontSize = 17.sp,
                    color = AppColors.mOffWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                )

                //choices
                Log.d("QuizDisplay", "QuizDisplay: $choicesState")
                choicesState.forEachIndexed { index, answerText ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp, brush = Brush.linearGradient(
                                    colors = listOf(AppColors.mOffDarkPurple, AppColors.mOffWhite)
                                ), shape = RoundedCornerShape(15.dp)
                            )

                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (answerState == index), onClick = {
                                updateAnswer(index)
                            }, modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor =
                                if (correctAnswerState == true && index == answerState) {
                                    Color.Green.copy(alpha = 0.2f)
                                } else {
                                    Color.Red.copy(alpha = 0.2f)
                                }
                            )
                        )
                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    color = if (correctAnswerState == true && index == answerState) {
                                        Color.Green
                                    } else if (correctAnswerState == false && index == answerState) {
                                        Color.Red
                                    } else {
                                        AppColors.mOffWhite
                                    }, fontSize = 17.sp
                                )
                            ) {
                                append(answerText)
                            }
                        }
                        Text(text = annotatedString, modifier = Modifier.padding(6.dp))

                    }

                }
                Button(
                    onClick = { onNextClicked(questionIndex.value) },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppColors.mLightBlue
                    )
                ) {
                    Text(
                        text = "Next", modifier = Modifier.padding(4.dp),
                        color = AppColors.mOffWhite, fontSize = 17.sp
                    )
                }
            }
        }

    }
}

@Composable
fun DrawDottedLine(pathEffect: PathEffect) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = AppColors.mLightGray, start = Offset(0f, 0f),
            end = Offset(size.width, 0f), pathEffect = pathEffect
        )
    }

}

//@Preview
@Composable
fun QuizTracker(counter: Int, outOf: Int) {
    Text(text = buildAnnotatedString {

        withStyle(
            style = SpanStyle(
                color = AppColors.mLightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 27.sp
            )
        ) {
            append("Question $counter/")
            withStyle(
                style = SpanStyle(
                    color = AppColors.mLightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            ) {
                append("$outOf")
            }
        }

    }, modifier = Modifier.padding(20.dp))

}

@Preview
@Composable
fun ShowProgress(score: Int = 12) {
    val gradient = Brush.linearGradient(listOf(Color(0xfff95075), Color(0xffbe6be5)))

    val progressFactor by remember(score) {
        mutableStateOf(score * 0.005f)
    }
    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 4.dp, brush = Brush.linearGradient(
                    colors = listOf(

                        AppColors.mLightPurple, AppColors.mLightPurple
                    )
                ), shape = RoundedCornerShape(34.dp)
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color.Transparent), verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            contentPadding = PaddingValues(1.dp), onClick = { },
            modifier = Modifier
                .fillMaxWidth(progressFactor)
                .background(
                    brush = gradient
                ),
            enabled = false,
            elevation = null,
            colors = buttonColors(
                backgroundColor = Color.Transparent,
                disabledBackgroundColor = Color.Transparent
            )
        ) {
            Text(text = (score*10).toString(),
                modifier = Modifier.clip(shape = RoundedCornerShape(23.dp))
                    .fillMaxHeight(0.87f)
                    .fillMaxWidth()
                    .padding(6.dp),
                color = AppColors.mOffWhite,
                textAlign = TextAlign.Center)
        }
    }
}
