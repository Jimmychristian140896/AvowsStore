package com.jimmy.avowsstore.presentation.profile

sealed interface ProfileAction {
    data object Logout : ProfileAction
    data object OnTryAgain : ProfileAction
}