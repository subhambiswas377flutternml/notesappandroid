package com.aero.notesapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
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
import com.aero.notesapp.presentation.components.AssetSvgView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController){
    Scaffold(containerColor = colorResource(R.color.primary),
        topBar = { CenterAlignedTopAppBar(title = {
            AssetSvgView(imageUrl = stringResource(R.string.notely_name),
                modifier = Modifier.height(23.dp).width(80.dp))
        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = Color.Transparent
        )) }) { innerPadding->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(118.dp))
            // Banner Image
            AssetSvgView(imageUrl = stringResource(R.string.start_banner_svg),
                modifier = Modifier.height(197.dp).width(268.dp))

            Spacer(modifier = Modifier.height(28.dp))

            // Banner Text
            Text(text = stringResource(R.string.banner_title),
                color = colorResource(R.color.textBlack),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Banner Description
            Text(text = stringResource(R.string.banner_description),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.textBrown)
            )

            Spacer(modifier = Modifier.height(88.dp))
            // Button
            ElevatedButton(onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.secondary),
                    contentColor = colorResource((R.color.tertiary)),),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 28.dp),
                contentPadding = PaddingValues(vertical = 24.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp, 3.dp, 0.dp, 0.dp, 0.dp)) {
                Text(text = "GET STARTED",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Text Button
            Text(text = "Already have an account?",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = colorResource(R.color.secondary),
                modifier = Modifier.clickable {  }
            )
        }
    }
}