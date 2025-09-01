package com.aero.notesapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aero.notesapp.core.request.LoginRequest
import com.aero.notesapp.core.request.SignupRequest
import com.aero.notesapp.domain.model.UserModel
import com.aero.notesapp.domain.usecase.auth.CheckAuthUsecase
import com.aero.notesapp.domain.usecase.auth.LoginUsecase
import com.aero.notesapp.domain.usecase.auth.SignupUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class AuthState{
    data object Initial: AuthState()
    data object Loading: AuthState()
    data class Authenticated(val authModel: UserModel): AuthState()
    data class Error(val ex: Throwable):AuthState()
    data object UnAuthenticated: AuthState()
}

fun AuthState.isLoading(): Boolean{
    when(this){
        AuthState.Loading->{
            return true
        }else->{
            return false
        }
    }
}

fun AuthState.userId(): Int?{
    when(this){
        is AuthState.Authenticated->{
            return this.userId()
        }else->{
            return null
        }
    }
}

@HiltViewModel
class AuthViewModel @Inject constructor(private val loginUsecase: LoginUsecase,
                                        private val checkAuthUsecase: CheckAuthUsecase,
                                        private val signupUsecase: SignupUsecase
): ViewModel(){
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

    fun checkAuth(){
        _state.value = AuthState.Loading
        viewModelScope.launch(handler) {
            withContext(Dispatchers.IO){
                try{
                    val userData = checkAuthUsecase()
                    if(userData!=null){
                        _state.value = AuthState.Authenticated(userData)
                    }else{
                        _state.value = AuthState.UnAuthenticated
                    }
                }catch(ex: Exception){
                    throw ex
                }
            }
        }
    }

    fun singup(signupRequest: SignupRequest){
        _state.value = AuthState.Loading
        viewModelScope.launch(handler) {
            withContext(Dispatchers.IO){
                try{
                    val authResponse = signupUsecase(signupRequest)
                    _state.value = AuthState.Authenticated(authResponse)
                }catch(ex:Exception){
                    throw ex
                }
            }
        }
    }
}