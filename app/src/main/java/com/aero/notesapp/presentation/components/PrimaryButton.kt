package com.aero.notesapp.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aero.notesapp.R
import com.aero.notesapp.ui.theme.CustomFontFamily

@Composable
fun PrimaryButton(buttonText: String, onClick:()->Unit){
    ElevatedButton(onClick = {
        onClick()
    },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.secondary),
            contentColor = colorResource((R.color.tertiary)),),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 28.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp, 3.dp, 0.dp, 0.dp, 0.dp)) {
        Text(text = buttonText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            fontFamily = CustomFontFamily.fontNunito)
    }
}