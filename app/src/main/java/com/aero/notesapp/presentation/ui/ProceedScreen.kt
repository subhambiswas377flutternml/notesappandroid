package com.aero.notesapp.presentation.ui

import android.widget.Space
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
import com.aero.notesapp.presentation.components.AssetSvgView
import com.aero.notesapp.presentation.components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProceedScreen(navController: NavHostController){
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            AssetSvgView(imageUrl = stringResource(R.string.notely_name), modifier = Modifier.height(23.dp).width(80.dp))
        }, colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent))
    }, containerColor = colorResource(R.color.primary)) { innerPadding->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){

            Spacer(modifier = Modifier.height(118.dp))

            AssetSvgView(imageUrl = stringResource(R.string.stepper_banner),
                modifier = Modifier.height(221.dp).width(268.dp))

            Spacer(modifier = Modifier.height(39.dp))

            Text(text = "Create Your First Note",
                color = colorResource(R.color.textBlack),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = stringResource(R.string.proceed_description),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.textBrown)
            )

            Spacer(modifier = Modifier.height(77.dp))

            PrimaryButton(onClick = {
                navController.navigate(Routes.GetStartedRoute)
            }, buttonText = "Proceed")
        }
    }
}