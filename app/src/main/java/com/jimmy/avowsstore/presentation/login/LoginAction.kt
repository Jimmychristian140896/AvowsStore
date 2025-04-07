package com.jimmy.avowsstore.presentation.login

sealed interface LoginAction {
    data object OnLoginClicked: LoginAction
    data class OnUsernameChanged(val username: String): LoginAction
    data class OnPasswordChanged(val password: String): LoginAction
    data object OnPasswordVisibleChange: LoginAction
}