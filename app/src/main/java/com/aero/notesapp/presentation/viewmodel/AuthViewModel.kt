package com.aero.notesapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aero.notesapp.core.request.LoginRequest
import com.aero.notesapp.domain.model.AuthModel
import com.aero.notesapp.domain.usecase.LoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class AuthState{
    data object Initial: AuthState()
    data object Loading: AuthState()
    data class Authenticated(val authModel: AuthModel): AuthState()
    data class Error(val ex: Throwable):AuthState()
    data object UnAuthenticated: AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(private val loginUsecase: LoginUsecase): ViewModel(){
    private val _state: MutableState<AuthState> = mutableStateOf<AuthState>(value = AuthState.Initial)
    val state: State<AuthState> = _state

    private val handler = CoroutineExceptionHandler{ _, throwable->
        _state.value = AuthState.Error(ex = throwable)
    }

    fun login(loginRequest: LoginRequest){
        _state.value = AuthState.Loading
        viewModelScope.launch(handler) {
            withContext(Dispatchers.IO){
                try{
                    val userData = loginUsecase(loginRequest)
                    _state.value = AuthState.Authenticated(authModel = userData)
                }catch (ex: Exception){
                    throw ex
                }
            }
        }
    }
}