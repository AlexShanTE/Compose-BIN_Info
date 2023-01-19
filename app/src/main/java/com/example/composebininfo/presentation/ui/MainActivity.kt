package com.example.composebininfo.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.composebininfo.R
import com.example.composebininfo.presentation.ui.mainscreen.BottomNavItem
import com.example.composebininfo.presentation.ui.mainscreen.BottomNavigationBar
import com.example.composebininfo.presentation.ui.mainscreen.MainScreen
import com.example.composebininfo.presentation.ui.mainscreen.Screen
import com.example.composebininfo.presentation.ui.theme.ComposeBINInfoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBINInfoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navHostController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Home",
                                        route = Screen.RequestScreen.route,
                                        icon = Icons.Default.Home
                                    ),
                                    BottomNavItem(
                                        name = "History",
                                        route = Screen.HistoryScreen.route,
                                        icon = ImageVector.vectorResource(R.drawable.ic_history)
                                    ),
                                ),
                                navController = navHostController,
                                onItemCLick = { bottomNavItem ->
                                    navHostController.navigate(bottomNavItem.route)
                                }
                            )
                        }
                    ) {
                        MainScreen(navController = navHostController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBINInfoTheme {
        MainScreen(navController = rememberNavController())
    }
}