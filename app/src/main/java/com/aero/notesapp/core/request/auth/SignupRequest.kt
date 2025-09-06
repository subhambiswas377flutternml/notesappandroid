package com.aero.notesapp.core.request.auth

data class SignupRequest(
    val name:String,
    val userName: String,
    val password: String,
)