package com.aero.notesapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aero.notesapp.R

@Composable
fun CustomInputField(hintText: String, labelText: String, controller: MutableState<String>, modifier: Modifier=Modifier){
   Column(modifier = modifier.fillMaxWidth()) {

       Text(text = labelText,
           fontSize = 12.sp,
           fontWeight = FontWeight.Bold,
           color = colorResource(R.color.textBlack)
       )

       Spacer(modifier = Modifier.height(8.dp))

       OutlinedTextField(value = controller.value,
           onValueChange = {
               controller.value=it
           },
           textStyle = TextStyle(
               color = colorResource(R.color.textBlack),
               fontWeight = FontWeight.Bold,
               fontSize = 16.sp,
           ),
           modifier = Modifier.fillMaxWidth(),
           shape = RoundedCornerShape(12.dp),
           placeholder = {
               Text(text=hintText,
                   color = colorResource(R.color.textBrown),
                   fontWeight = FontWeight.Bold,
                   fontSize = 16.sp,
               )
           },
           colors = TextFieldDefaults.colors().copy(
               unfocusedContainerColor = colorResource(R.color.tertiary),
               disabledContainerColor = colorResource(R.color.tertiary),
               focusedContainerColor = colorResource(R.color.tertiary),
               focusedIndicatorColor = Color.Transparent,
               disabledIndicatorColor = Color.Transparent,
               unfocusedIndicatorColor = Color.Transparent,
               focusedLabelColor = colorResource(R.color.textBrown),
               unfocusedLabelColor = colorResource(R.color.textBrown),
           )
       )
   }
}