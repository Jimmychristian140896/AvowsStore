package com.jimmy.avowsstore.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val token: String
)
