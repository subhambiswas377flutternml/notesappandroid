package com.aero.notesapp.presentation.ui

import android.widget.PopupMenu
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.aero.notesapp.R
import com.aero.notesapp.presentation.components.AssetSvgView
import com.aero.notesapp.ui.theme.CustomFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesDetailScreen(navController: NavHostController){

    val titleController: MutableState<String> = rememberSaveable{ mutableStateOf<String>(value = "Cerebral palsy sport") }
    val descriptionController: MutableState<String> = rememberSaveable{ mutableStateOf<String>(value = "Cerebral palsy sport classification is a classification system used by sports that include people with cerebral palsy (CP) with different degrees of severity to compete fairly against each other and against others with different types of disabilities. In general, Cerebral Palsy-International Sports and Recreation Association (CP-ISRA) serves as the body in charge of classification for cerebral palsy sport, though some sports have their own classification systems which apply to CP sportspeople|") }
    val showPopupMenu: MutableState<Boolean> = rememberSaveable{ mutableStateOf<Boolean>(value = false) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            title = {
            Text(text = "Edit Note",
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = colorResource(R.color.textBlack)
            )
        }, navigationIcon = { AssetSvgView(imageUrl = stringResource(R.string.arrow_back), modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .clickable {
                    navController.navigateUp()
                }) },
            actions = {
                AssetSvgView(imageUrl = stringResource(R.string.more_vert),
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                        .clickable {
                            showPopupMenu.value = true
                        })

                DropdownMenu(
                    expanded = showPopupMenu.value,
                    onDismissRequest = { showPopupMenu.value = false },
                    properties = PopupProperties(focusable = true),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    DropdownMenuItem(onClick = {
                        showPopupMenu.value=false
                    }, text = {
                        Text(text = "Save")
                    })
                }
            }, colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent))
    }, containerColor = colorResource(R.color.primary)) { innerPadding->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.Start
            ) {

            Spacer(modifier = Modifier.height(16.dp))
            BasicTextField(modifier = Modifier.fillMaxWidth(),
                value = titleController.value, onValueChange = {
                    titleController.value = it
                },
                textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Black, color = colorResource(R.color.textBlack),
                    fontFamily = CustomFontFamily.fontNunito))

            Spacer(modifier = Modifier.height(10.dp))

            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = descriptionController.value,
                onValueChange = {
                    descriptionController.value=it
                },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.textBrown),
                    fontFamily = CustomFontFamily.fontNunito
                )
            )
        }
    }
}