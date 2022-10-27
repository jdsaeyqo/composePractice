package com.ssafy.readerapp.navigation

import com.google.common.math.Stats
import java.lang.IllegalStateException

enum class ReaderScreens {

    ReaderSplashScreen,
    LoginScreen,
    CreateAccountScreen,
    ReaderHomeScreen,
    SearchScreen,
    BookDetailScreen,
    UpdateScreen,
    StatsScreen;

    companion object {
        fun fromRoute(route: String?): ReaderScreens = when (route?.substringBefore("/")) {
            ReaderSplashScreen.name -> ReaderSplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> CreateAccountScreen
            ReaderHomeScreen.name -> ReaderHomeScreen
            SearchScreen.name -> SearchScreen
            BookDetailScreen.name -> BookDetailScreen
            UpdateScreen.name -> UpdateScreen
            StatsScreen.name -> StatsScreen
            null -> ReaderHomeScreen
            else -> throw IllegalStateException("Route $route is not recognized")
        }
    }

}