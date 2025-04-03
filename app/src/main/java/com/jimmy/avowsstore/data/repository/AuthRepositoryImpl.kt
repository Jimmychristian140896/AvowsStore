package com.jimmy.avowsstore.data.repository

import com.jimmy.avowsstore.core.data.Constant
import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Empty
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.core.network.constructRoute
import com.jimmy.avowsstore.core.network.safeCall
import com.jimmy.avowsstore.data.remote.dto.LoginDto
import com.jimmy.avowsstore.domain.mappers.toLogin
import com.jimmy.avowsstore.domain.model.Login
import com.jimmy.avowsstore.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthRepositoryImpl(
    private val client: HttpClient
): AuthRepository {
    override suspend fun login(username: String, password: String): Result<Login, DataError> {
        val result = safeCall<LoginDto> {
            client.post(
                urlString = constructRoute("/auth/login")
            ) {
                setBody(mapOf(
                    "username" to username,
                    "password" to password
                ))
            }
        }
        when(result) {
            is Result.Error -> {
                return Result.Error(result.error)
            }
            is Result.Success -> {
                return Result.Success(result.data.toLogin(
                    userId = Constant.USER_ID,
                    username = username
                ))
            }
        }
    }
}