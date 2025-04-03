package com.jimmy.avowsstore.domain.model

data class Login(
    val token: String,
    val userId: Int,
    val username: String
)
