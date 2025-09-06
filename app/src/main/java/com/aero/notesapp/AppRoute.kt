package com.aero.notesapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.aero.notesapp.presentation.viewmodel.AuthViewModel
import com.aero.notesapp.presentation.viewmodel.NotesViewModel
import kotlinx.serialization.Serializable

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
    data class NotesDetailRoute(val noteId: Int)
}

@Composable
fun App(){

    val authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
    val notesViewModel: NotesViewModel = hiltViewModel<NotesViewModel>()

    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashRoute) {

        composable<Routes.SplashRoute> {
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }

        composable<Routes.GetStartedRoute> {
            GetStartedScreen(navController = navController)
        }

        composable<Routes.ProceedRoute> {
            ProceedScreen(navController = navController)
        }

        composable<Routes.AuthRoute> {backStackEntry->
            val args = backStackEntry.toRoute<Routes.AuthRoute>()
            AuthScrreen(navController=navController, authMode = args.authMode, authViewModel = authViewModel)
        }

        composable<Routes.HomeRoute> {
            HomeScreen(navController = navController, authViewModel=authViewModel, notesViewModel = notesViewModel)
        }

        composable<Routes.NotesDetailRoute> {backStackEntry->
            val args = backStackEntry.toRoute<Routes.NotesDetailRoute>()
            NotesDetailScreen(navController = navController, noteId = args.noteId, notesViewModel = notesViewModel)
        }
    }
}