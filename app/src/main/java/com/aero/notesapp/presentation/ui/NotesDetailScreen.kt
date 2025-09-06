package com.aero.notesapp.presentation.ui

import android.content.Context
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.aero.notesapp.R
import com.aero.notesapp.core.request.note.UpdateNoteRequest
import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.presentation.components.AssetSvgView
import com.aero.notesapp.presentation.viewmodel.AuthViewModel
import com.aero.notesapp.presentation.viewmodel.NotesFlowState
import com.aero.notesapp.presentation.viewmodel.NotesViewModel
import com.aero.notesapp.presentation.viewmodel.isLoading
import com.aero.notesapp.presentation.viewmodel.noteById
import com.aero.notesapp.presentation.viewmodel.userId
import com.aero.notesapp.ui.theme.CustomFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesDetailScreen(navController: NavHostController, noteId: Int?, notesViewModel: NotesViewModel, authViewModel: AuthViewModel, createNote: Boolean){

    val note: NotesModel? = notesViewModel.state.value.noteById(noteId?:0)

    val titleController: MutableState<String> = rememberSaveable{ mutableStateOf<String>(value = note?.title?:"") }
    val descriptionController: MutableState<String> = rememberSaveable{ mutableStateOf<String>(value = note?.description?:"") }
    val showPopupMenu: MutableState<Boolean> = rememberSaveable{ mutableStateOf<Boolean>(value = false) }

    val appContext: Context = LocalContext.current

    LaunchedEffect(Unit) {
        notesViewModel.flowState.collect{event->
            when(event){
                is NotesFlowState.NavigateBack->{
                    Toast.makeText(appContext, "Success", Toast.LENGTH_LONG).show()
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            title = {
            Text(text = if(createNote) "Create Mote" else "Edit Note",
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

                        if(note?.id!=null||titleController.value.trim().isNotEmpty()||descriptionController.value.trim().isNotEmpty()){
                            if(createNote){
                                if(authViewModel.state.value.userId()!=null){
                                    notesViewModel.addNoteByUser(
                                        userId = authViewModel.state.value.userId()!!,
                                        title = titleController.value.trim(),
                                        description = descriptionController.value.trim()
                                    )
                                }else{
                                    Toast.makeText(appContext, "User couldn't be found !", Toast.LENGTH_LONG).show()
                                }
                            }else{
                                notesViewModel.updateNote(noteId = note?.id?:1,
                                    updateNoteRequest = UpdateNoteRequest(
                                        title = titleController.value.trim(),
                                        description = descriptionController.value.trim()
                                    )
                                )
                            }
                            showPopupMenu.value=false
                        }else{
                            Toast.makeText(appContext, "Title and Description can't be empty !", Toast.LENGTH_LONG).show()
                        }
                    }, text = {
                        Text(text = "Save")
                    });

                    if(!createNote){
                        DropdownMenuItem(onClick = {
                            if(noteId!=null){
                                notesViewModel.deleteByNoteId(noteId)
                            }else{
                                Toast.makeText(appContext, "Note couldn't be deleted !", Toast.LENGTH_LONG).show()
                            }
                        }, text = {
                            Text(text = "Delete",
                                color = Color.Red)
                        })
                    }
                }
            }, colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent))
    }, containerColor = colorResource(R.color.primary)) { innerPadding->
        Box {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {

                Spacer(modifier = Modifier.height(16.dp))
                BasicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = titleController.value, onValueChange = {
                        titleController.value = it
                    },
                    decorationBox = {innerTextField ->
                        if(titleController.value.trim().isEmpty()){
                            Text("Title",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.Gray,
                                    fontFamily = CustomFontFamily.fontNunito
                                )
                                )
                        }
                        innerTextField()
                    },
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        color = colorResource(R.color.textBlack),
                        fontFamily = CustomFontFamily.fontNunito
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                BasicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = descriptionController.value,
                    onValueChange = {
                        descriptionController.value = it
                    },
                    decorationBox = {innerTextField ->
                        if(descriptionController.value.trim().isEmpty()){
                            Text("description......",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray,
                                    fontFamily = CustomFontFamily.fontNunito
                                ))
                        }
                        innerTextField()
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.textBrown),
                        fontFamily = CustomFontFamily.fontNunito
                    )
                )
            };

            if(notesViewModel.state.value.isLoading()){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = colorResource(R.color.secondary))
                }
            };
        }
    }
}