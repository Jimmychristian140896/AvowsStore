package com.jimmy.avowsstore.presentation.login

import com.jimmy.avowsstore.core.data.UiText

sealed interface LoginEvent {
    data object OnLoginSuccess: LoginEvent
    data class OnLoginFailed(val message: UiText): LoginEvent
}