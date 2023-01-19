package com.example.composebininfo.presentation.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.composebininfo.presentation.ui.bininfoscreen.BinInfoScreen
import com.example.composebininfo.presentation.ui.historyscreen.HistoryScreen
import com.example.composebininfo.presentation.ui.homescreen.HomeScreen

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
            HistoryScreen()
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

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    navController: NavController,
    onItemCLick: (BottomNavItem) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val screensWithBottomBar = listOf(
        Screen.RequestScreen,
        Screen.HistoryScreen
    )

    val bottomBarDestination = screensWithBottomBar.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            modifier = modifier,
            elevation = 4.dp
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry?.destination?.route
                BottomNavigationItem(
                    selected = item.route == navController.currentDestination?.route,
                    onClick = {
                        if (currentDestination?.route == Screen.HistoryScreen.route) {
                            navController.popBackStack()
                        } else onItemCLick(item)
                    },
                    icon = {
                        if (selected) {
                            Column(
                                modifier = modifier,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                                Text(text = item.name)
                            }
                        } else Icon(imageVector = item.icon, contentDescription = item.name)
                    },
                )
            }
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
