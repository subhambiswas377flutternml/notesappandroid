package com.aero.notesapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aero.notesapp.presentation.ui.HomeScreen
import kotlinx.serialization.Serializable

object Routes{
    @Serializable
    data object HomeRoute
}

@Composable
fun App(){
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeRoute) {
        composable<Routes.HomeRoute> {
            HomeScreen(navController = navController)
        }
    }
}