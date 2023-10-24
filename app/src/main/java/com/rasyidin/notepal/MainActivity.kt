package com.rasyidin.notepal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rasyidin.notepal.ui.component.BottomNavBar
import com.rasyidin.notepal.ui.navigation.Screen
import com.rasyidin.notepal.ui.screen.home.HomeScreen
import com.rasyidin.notepal.ui.theme.NotePalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotePalTheme {
                NotePalApp()
            }
        }
    }
}

@Composable
fun NotePalApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    var bottomNavBarState by rememberSaveable { mutableStateOf(false) }
    val navBackStack by navController.currentBackStackEntryAsState()
    bottomNavBarState = when (navBackStack?.destination?.route) {
        Screen.Home.route,
        Screen.Finished.route,
        Screen.SearchNotes.route,
        Screen.Profile.route -> true
        else -> false
    }
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                navController = navController,
                showNavBar = bottomNavBarState,
                onAddClick = {

                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
            builder = {
                composable(Screen.Home.route) {
                    LaunchedEffect(Unit) {
                        bottomNavBarState = true
                    }
                    HomeScreen()
                }
                composable(Screen.Finished.route) {
                    LaunchedEffect(Unit) {
                        bottomNavBarState = true
                    }
                    HomeScreen()
                }
                composable(Screen.SearchNotes.route) {
                    LaunchedEffect(Unit) {
                        bottomNavBarState = true
                    }
                    HomeScreen()
                }
                composable(Screen.Profile.route) {
                    LaunchedEffect(Unit) {
                        bottomNavBarState = true
                    }
                    HomeScreen()
                }
            }
        )
    }
}