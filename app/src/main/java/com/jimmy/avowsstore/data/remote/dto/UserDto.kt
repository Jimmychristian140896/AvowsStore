package com.jimmy.avowsstore.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val username: String,
    val password: String
)
