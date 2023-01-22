package com.example.composebininfo.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composebininfo.presentation.ui.details.DetailsScreen
import com.example.composebininfo.presentation.ui.history.HistoryScreen
import com.example.composebininfo.presentation.ui.home.HomeScreen

@Composable
fun MainScreen(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.RequestScreen.route
    ) {
        composable(route = Screen.RequestScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.HistoryScreen.route) {
            HistoryScreen(navController = navController)
        }
        composable(
            route = Screen.BinInfoScreen.route + "/{BIN}",
            arguments = listOf(
                navArgument(name = "BIN") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("BIN")
                ?.let { DetailsScreen(bin = it) }
        }
    }
}

sealed class Screen(val route: String) {
    object RequestScreen : Screen("request screen")
    object BinInfoScreen : Screen("bin inf screen")
    object HistoryScreen : Screen("history screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/${arg}")
            }
        }
    }
}
