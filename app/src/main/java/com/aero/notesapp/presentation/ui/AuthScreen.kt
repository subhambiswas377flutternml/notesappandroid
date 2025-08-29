package com.aero.notesapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aero.notesapp.R
import com.aero.notesapp.Routes
import com.aero.notesapp.core.AuthMode
import com.aero.notesapp.presentation.components.AssetSvgView
import com.aero.notesapp.presentation.components.CustomInputField
import com.aero.notesapp.presentation.components.PrimaryButton
import com.aero.notesapp.presentation.components.PrimaryTextButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScrreen(navController: NavHostController, authMode: AuthMode){
    val nameController: MutableState<String> = rememberSaveable {mutableStateOf<String>(value = "")}
    val emailController: MutableState<String> = rememberSaveable{ mutableStateOf<String>(value = "") }
    val passwordController: MutableState<String> = remember { mutableStateOf<String>(value = "") }

    val currentAuthState: MutableState<AuthMode> = rememberSaveable{ mutableStateOf<AuthMode>(value = authMode) }

    Scaffold(topBar = { CenterAlignedTopAppBar(title = {
        AssetSvgView(imageUrl = stringResource(R.string.notely_name), modifier = Modifier.height(23.dp).width(80.dp))
    }, colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent)) }, containerColor = colorResource(R.color.primary)) { innerPadding->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Create a free account",
                fontSize = 24.sp,
                color = colorResource(R.color.textBlack),
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )

            Text(text = stringResource(R.string.auth_note),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.textBrown),
                textAlign = TextAlign.Center
            )

            if(currentAuthState.value==AuthMode.SignUp)
            Spacer(modifier = Modifier.height(52.dp))

            if(currentAuthState.value==AuthMode.SignUp)
            CustomInputField(
                hintText = "John Doe",
                labelText = "Full Name",
                controller = nameController,
                modifier = Modifier.padding(horizontal = 28.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomInputField(
                hintText = "abc@xyz.com",
                labelText = "Email Address",
                controller = emailController,
                modifier = Modifier.padding(horizontal = 28.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomInputField(
                hintText = "Password",
                labelText = "Password",
                controller = passwordController,
                modifier = Modifier.padding(horizontal = 28.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(onClick = {
                navController.navigate(Routes.HomeRoute)
            }, buttonText = if(currentAuthState.value==AuthMode.SignUp) "Create Account" else "Login")

            Spacer(modifier = Modifier.height(20.dp))

            PrimaryTextButton(buttonText = if(currentAuthState.value!=AuthMode.SignUp)"Are you New here?" else "Already have an account?", onClick = {
                if(currentAuthState.value==AuthMode.SignUp){
                    currentAuthState.value = AuthMode.Login
                }else{
                    currentAuthState.value = AuthMode.SignUp
                }
            })

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}