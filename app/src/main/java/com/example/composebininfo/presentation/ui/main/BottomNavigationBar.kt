package com.example.composebininfo.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

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
                        if (!selected) {
                            if (currentDestination?.route == Screen.HistoryScreen.route) {
                                navController.popBackStack()
                            } else onItemCLick(item)
                        }
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