package com.aero.notesapp.presentation.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aero.notesapp.R
import com.aero.notesapp.Routes
import com.aero.notesapp.presentation.components.AssetImageView
import com.aero.notesapp.presentation.viewmodel.AuthState
import com.aero.notesapp.presentation.viewmodel.AuthViewModel

@Composable
fun SplashScreen(navController:NavHostController, authViewModel: AuthViewModel){
    val context: Context = LocalContext.current

    LaunchedEffect(Unit) {
        authViewModel.checkAuth()
    }

    LaunchedEffect(authViewModel.state.value) {
        when(authViewModel.state.value){
            is AuthState.Authenticated->{
                navController.navigate(Routes.HomeRoute)
            }
            AuthState.UnAuthenticated->{
                navController.navigate(Routes.ProceedRoute)
            }
            is AuthState.Error->{
                Toast.makeText(context, "Something went wrong !", Toast.LENGTH_LONG).show()
                navController.navigate(Routes.ProceedRoute)
            }
            else->{}
        }
    }

    Scaffold(containerColor = colorResource(R.color.primary)) { innerPadding->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()){

            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                AssetImageView(imagePath = stringResource(R.string.splash_icon),
                    modifier = Modifier.height(150.dp).width(150.dp))
            }

            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.weight(1f))
                when(authViewModel.state.value){
                    AuthState.Loading->{
                        CircularProgressIndicator(color = colorResource(R.color.secondary))
                    }
                    else->{
                        Spacer(modifier = Modifier.height(0.dp))
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}