package com.aero.notesapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aero.notesapp.R

@Composable
fun NoteCard(title:String, description: String, modifier: Modifier = Modifier){
    Box(modifier = modifier.clip(shape = RoundedCornerShape(12.dp)).background(color = colorResource(
        R.color.tertiary)
    ).width(120.dp),) {
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