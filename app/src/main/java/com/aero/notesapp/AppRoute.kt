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
import com.aero.notesapp.presentation.ui.NotesDetailScreen
import com.aero.notesapp.presentation.ui.ProceedScreen
import com.aero.notesapp.presentation.ui.SplashScreen
import kotlinx.serialization.Serializable
import okhttp3.Route

object Routes{
    @Serializable
    data object SplashRoute

    @Serializable
    data object GetStartedRoute

    @Serializable
    data object ProceedRoute

    @Serializable
    data class AuthRoute(val authMode: AuthMode)

    @Serializable
    data object HomeRoute

    @Serializable
    data object NotesDetailRoute
}

@Composable
fun App(){
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashRoute) {

        composable<Routes.SplashRoute> {
            SplashScreen(navController = navController)
        }

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

        composable<Routes.NotesDetailRoute> {
            NotesDetailScreen(navController = navController)
        }
    }
}