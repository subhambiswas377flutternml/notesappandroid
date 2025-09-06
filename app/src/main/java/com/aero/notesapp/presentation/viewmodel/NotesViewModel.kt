package com.aero.notesapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aero.notesapp.core.request.note.AddNoteRequest
import com.aero.notesapp.core.request.note.UpdateNoteRequest
import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.usecase.notes.AddNoteByUserUseCase
import com.aero.notesapp.domain.usecase.notes.DeleteByNoteIdUseCase
import com.aero.notesapp.domain.usecase.notes.FetchNotesFromLocalUseCase
import com.aero.notesapp.domain.usecase.notes.GetNotesByUserUsecase
import com.aero.notesapp.domain.usecase.notes.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class NotesState{
    data object Initial:NotesState()
    data object Loading: NotesState()
    data class Loaded(val notes: List<NotesModel>): NotesState()
    data class Error(val ex: Exception): NotesState()
}

sealed class NotesFlowState{
    data object NavigateBack: NotesFlowState()
}

fun NotesState.isLoading(): Boolean{
    when(this){
        NotesState.Loading->{
            return true
        }
        else->{
            return false
        }
    }
}

fun NotesState.noteById(id: Int): NotesModel?{
    when(this){
        is NotesState.Loaded->{
            val notesList: List<NotesModel> = this.notes
            for(i: Int in 0 until notesList.size){
                if(notesList[i].id==id){
                    return notesList[i]
                }
            }
            return null
        }
        else->{
            return null
        }
    }
}

@HiltViewModel
class NotesViewModel @Inject constructor(private val getNotesByUserUsecase: GetNotesByUserUsecase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val addNoteByUserUseCase: AddNoteByUserUseCase,
    private val deleteByNoteIdUseCase: DeleteByNoteIdUseCase,
    private val fetchNotesFromLocalUseCase: FetchNotesFromLocalUseCase): ViewModel(){

    private val _state: MutableState<NotesState> = mutableStateOf<NotesState>(value = NotesState.Initial)
    val state: State<NotesState> = _state

    private val _flowState: MutableSharedFlow<NotesFlowState> = MutableSharedFlow<NotesFlowState>(extraBufferCapacity = 1)
    val flowState: SharedFlow<NotesFlowState> = _flowState.asSharedFlow()

    val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable->
        _state.value = NotesState.Error(ex = Exception())
    }

    fun fetchNotesFromLocal(userId: Int?){
        _state.value=NotesState.Loading
        viewModelScope.launch(exceptionHandler){
            withContext(Dispatchers.IO){
               try{
                   val localNotesList: List<NotesModel> = fetchNotesFromLocalUseCase()
                   _state.value = NotesState.Loaded(notes = localNotesList)

                   getNotesByUser(userId = userId, syncInBackground = localNotesList.isNotEmpty())
               }catch(ex: Exception){
                   throw ex
               }

            }
        }
    }

    fun getNotesByUser(userId:Int?, syncInBackground:Boolean=false){
       if(!syncInBackground){
           _state.value = NotesState.Loading
       }
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

    fun updateNote(noteId: Int, updateNoteRequest: UpdateNoteRequest){
        _state.value = NotesState.Loading
        viewModelScope.launch(exceptionHandler+Dispatchers.IO) {
            try{
                val updateResponse = updateNoteUseCase(noteId = noteId, updateNoteRequest=updateNoteRequest)
                _state.value = NotesState.Loaded(notes = updateResponse)
                _flowState.emit(value = NotesFlowState.NavigateBack)
            }catch(ex: Exception){
                throw ex
            }
        }
    }

    fun addNoteByUser(userId: Int, title: String, description:String){
        _state.value=NotesState.Loading
        viewModelScope.launch(exceptionHandler+Dispatchers.IO) {
            try{
                val notesList = addNoteByUserUseCase(addNoteRequest = AddNoteRequest(
                    userId = userId,
                    title = title,
                    description=description,
                ))
                _state.value=NotesState.Loaded(notes = notesList)
                _flowState.emit(NotesFlowState.NavigateBack)
            }catch(ex: Exception){
                throw ex
            }
        }
    }

    fun deleteByNoteId(noteId: Int){
        _state.value=NotesState.Loading
        viewModelScope.launch(exceptionHandler+Dispatchers.IO) {
            try{
                val notesList = deleteByNoteIdUseCase(noteId = noteId)
                _state.value=NotesState.Loaded(notes = notesList)
                _flowState.emit(NotesFlowState.NavigateBack)
            }catch(ex: Exception){
                throw ex
            }
        }
    }
}