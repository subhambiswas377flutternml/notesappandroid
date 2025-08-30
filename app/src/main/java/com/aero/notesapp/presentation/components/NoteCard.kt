package com.aero.notesapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.createRippleModifierNode
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aero.notesapp.R
import com.aero.notesapp.Routes

@Composable
fun NoteCard(title:String, description: String, navController: NavHostController){

    Box(modifier = Modifier
        .width(120.dp)
        .clip(shape = RoundedCornerShape(12.dp))
        .background(color = colorResource(R.color.tertiary), shape = RoundedCornerShape(12.dp))
        .clickable(indication = ripple(color = colorResource(R.color.secondary)),
            interactionSource = remember { MutableInteractionSource() }) {
            navController.navigate(Routes.NotesDetailRoute)
        },
        ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title,
                color = colorResource(R.color.textBlack),
                fontWeight = FontWeight.Black,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = description,
                fontSize = 14.sp,
                maxLines = 5,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.textBrown)
            )
        }
    }
}