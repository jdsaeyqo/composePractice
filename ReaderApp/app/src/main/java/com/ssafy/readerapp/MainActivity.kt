package com.ssafy.readerapp

import android.os.Bundle
import android.util.Log
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.ssafy.readerapp.navigation.ReaderNavigation
import com.ssafy.readerapp.ui.theme.ReaderAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.Reader

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaderAppTheme {
                // A surface container using the 'background' color from the theme
                ReaderApp()
            }
        }
    }
}

@Composable
fun ReaderApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                ReaderNavigation()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReaderAppTheme {
        ReaderApp()
    }
}