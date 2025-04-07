package com.jimmy.avowsstore.presentation.profile

sealed interface ProfileEvent {
    data object Logout : ProfileEvent

}