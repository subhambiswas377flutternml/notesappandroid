package com.aero.notesapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aero.notesapp.core.AuthMode
import com.aero.notesapp.presentation.ui.AuthScrreen
import com.aero.notesapp.presentation.ui.GetStartedScreen
import com.aero.notesapp.presentation.ui.HomeScreen
import com.aero.notesapp.presentation.ui.ProceedScreen
import kotlinx.serialization.Serializable

object Routes{
    @Serializable
    data object GetStartedRoute

    @Serializable
    data object ProceedRoute

    @Serializable
    data class AuthRoute(val authMode: AuthMode)

    @Serializable
    data object HomeRoute
}

@Composable
fun App(){
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ProceedRoute) {

        composable<Routes.GetStartedRoute> {
            GetStartedScreen(navController = navController)
        }

        composable<Routes.ProceedRoute> {
            ProceedScreen(navController = navController)
        }

        composable<Routes.AuthRoute> {backStackEntry->
            val args = backStackEntry.toRoute<Routes.AuthRoute>()
            AuthScrreen(navController=navController, authMode = args.authMode)
        }

        composable<Routes.HomeRoute> {
            HomeScreen(navController = navController)
        }
    }
}