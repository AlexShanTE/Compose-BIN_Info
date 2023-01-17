package com.example.composebininfo.presentation.ui.bininfoapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composebininfo.presentation.ui.bininfoscreen.BinInfoScreen
import com.example.composebininfo.presentation.ui.requestscreen.RequestScreen

@Composable
fun BinInfoApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RequestScreen.route) {
        composable(route = Screen.RequestScreen.route) {
            RequestScreen(navController = navController)
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
                ?.let { BinInfoScreen(bin = it) }
        }
    }
}

sealed class Screen(val route: String) {
    object RequestScreen : Screen("request screen")
    object BinInfoScreen : Screen("bin inf screen")

    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/${arg}")
            }
        }
    }
}