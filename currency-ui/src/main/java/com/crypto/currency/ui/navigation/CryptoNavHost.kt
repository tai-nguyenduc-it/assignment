package com.crypto.currency.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.crypto.currency.ui.navigation.route.Route.Home
import com.crypto.currency.ui.navigation.route.Route.Splash
import com.crypto.currency.ui.screen.home.HomeScreen
import com.crypto.currency.ui.screen.splash.SplashScreen

@Composable
fun CryptoNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen()
        }
        composable<Home> {
            HomeScreen()
        }
    }
}
