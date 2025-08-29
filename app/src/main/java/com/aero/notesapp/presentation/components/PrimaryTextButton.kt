package com.aero.notesapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aero.notesapp.R

@Composable
fun PrimaryTextButton(buttonText:String, onClick:()->Unit){
    Text(text = buttonText,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        color = colorResource(R.color.secondary),
        modifier = Modifier.clickable {
            onClick()
        }
    )
}