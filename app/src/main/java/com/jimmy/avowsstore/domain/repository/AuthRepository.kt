package com.jimmy.avowsstore.domain.repository

import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.data.remote.dto.LoginDto
import com.jimmy.avowsstore.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<LoginDto, DataError>
}