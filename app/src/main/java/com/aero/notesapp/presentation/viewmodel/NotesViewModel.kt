package com.aero.notesapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.usecase.notes.GetNotesByUserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class NotesState{
    data object Initial:NotesState()
    data object Loading: NotesState()
    data class Loaded(val notes: List<NotesModel>): NotesState()
    data class Error(val ex: Exception): NotesState()
}

@HiltViewModel
class NotesViewModel @Inject constructor(private val getNotesByUserUsecase: GetNotesByUserUsecase): ViewModel(){
    private val _state: MutableState<NotesState> = mutableStateOf<NotesState>(value = NotesState.Initial)
    val state: State<NotesState> = _state

    val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable->
        _state.value = NotesState.Error(ex = Exception())
    }

    fun getNotesByUser(userId:Int?){
        _state.value = NotesState.Loading
        viewModelScope.launch(exceptionHandler) {
            if(userId==null){
                throw Exception()
            }
            else{
                withContext(Dispatchers.IO){
                    try{
                        val notes = getNotesByUserUsecase(userId)
                        _state.value = NotesState.Loaded(notes = notes)
                    }catch(ex:Exception){
                        throw ex
                    }
                }
            }
        }
    }
}