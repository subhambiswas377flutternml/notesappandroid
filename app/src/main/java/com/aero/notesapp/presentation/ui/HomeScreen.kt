package com.aero.notesapp.presentation.ui

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.presentation.components.AssetSvgView
import com.aero.notesapp.presentation.components.NoteCard
import com.aero.notesapp.presentation.viewmodel.AuthViewModel
import com.aero.notesapp.presentation.viewmodel.NotesState
import com.aero.notesapp.presentation.viewmodel.NotesViewModel
import com.aero.notesapp.presentation.viewmodel.userId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController,
               authViewModel: AuthViewModel,
               notesViewModel: NotesViewModel){


    LaunchedEffect(Unit) {
        notesViewModel.getNotesByUser(authViewModel.state.value.userId())
    }

    val gridState = rememberLazyGridState()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            title = { Text(text= "All Notes",
                fontWeight = FontWeight.Black,
                fontSize = 14.sp,
                color = colorResource(R.color.textBlack)
            ) },
            colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent),
            navigationIcon = { AssetSvgView(imageUrl = stringResource(R.string.drawer_icon), modifier = Modifier.height(20.dp).width(20.dp)) },
            actions = {
                AssetSvgView(imageUrl = stringResource(R.string.search_icon), modifier = Modifier.height(20.dp).width(20.dp))
            }
        )
    }, containerColor = colorResource(R.color.primary)) { innerPadding->
       when(notesViewModel.state.value){
           NotesState.Initial->{}
           NotesState.Loading->{
               Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                   CircularProgressIndicator(color = colorResource(R.color.secondary))
               }
           }
           is NotesState.Loaded->{
               val notes: List<NotesModel> = (notesViewModel.state.value as NotesState.Loaded).notes

               LazyVerticalGrid(
                   modifier = Modifier.padding(innerPadding),
                   columns = GridCells.Adaptive(minSize = 120.dp),
                   state = gridState,
                   contentPadding = PaddingValues(horizontal = 22.dp, vertical = 26.dp),
                   verticalArrangement = Arrangement.spacedBy(8.dp),
                   horizontalArrangement = Arrangement.spacedBy(12.dp),
               ){
                   items(notes){element->
                       NoteCard(
                           notesModel = element,
                           navController = navController
                       )
                   }
               }

           }

           is NotesState.Error->{
               Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                   Text(text = "Something went wrong!",
                       color = colorResource(R.color.textBlack),
                       textAlign = TextAlign.Center,
                       fontWeight = FontWeight.Black,
                       fontSize = 20.sp
                   )
               }
           }
       }
    }
}