package com.jimmy.avowsstore.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,

)