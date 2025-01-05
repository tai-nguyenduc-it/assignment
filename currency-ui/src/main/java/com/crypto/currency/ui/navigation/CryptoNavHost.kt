package com.crypto.currency.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.crypto.currency.ui.navigation.route.Route.Currency
import com.crypto.currency.ui.navigation.route.Route.Home
import com.crypto.currency.ui.navigation.route.Route.Splash
import com.crypto.currency.ui.screen.currency.CurrencyScreen
import com.crypto.currency.ui.screen.home.HomeScreen
import com.crypto.currency.ui.screen.splash.SplashScreen

@Composable
fun CryptoNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onFinished = { navController.navigate(Home) }
            )
        }
        composable<Home> {
            HomeScreen(
                onCurrencyListAction = { type -> navController.navigate(Currency(type)) }
            )
        }
        composable<Currency>(
            typeMap = Currency.navTypeMap
        ) {
            val currencyType = it.toRoute<Currency>().currencyType
            CurrencyScreen(
                currencyType = currencyType,
                onBackAction = navController::navigateUp
            )
        }
    }
}
