package com.jimmy.avowsstore.presentation.profile

import com.jimmy.avowsstore.core.data.UiText
import com.jimmy.avowsstore.domain.model.User

data class ProfileState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: UiText? = null

)