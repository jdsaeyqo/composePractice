package com.ssafy.readerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ssafy.readerapp.screens.ReaderSplashScreen
import com.ssafy.readerapp.screens.createAccount.CreateAccountScreen
import com.ssafy.readerapp.screens.details.BookDetailsScreen
import com.ssafy.readerapp.screens.home.HomeScreen
import com.ssafy.readerapp.screens.login.LoginScreen
import com.ssafy.readerapp.screens.search.SearchScreen
import com.ssafy.readerapp.screens.stats.StatsScreen
import com.ssafy.readerapp.screens.update.UpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.ReaderSplashScreen.name
    ) {
        composable(ReaderScreens.ReaderSplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(ReaderScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(ReaderScreens.CreateAccountScreen.name) {
            CreateAccountScreen(navController = navController)
        }
        composable(ReaderScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(ReaderScreens.BookDetailScreen.name) {
            BookDetailsScreen(navController = navController)
        }
        composable(ReaderScreens.UpdateScreen.name) {
            UpdateScreen(navController = navController)
        }
        composable(ReaderScreens.StatsScreen.name) {
            StatsScreen(navController = navController)
        }
    }
}