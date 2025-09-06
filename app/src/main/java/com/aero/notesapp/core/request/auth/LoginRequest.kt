package com.aero.notesapp.core.request.auth

data class LoginRequest(
    val userName: String,
    val password: String,
)