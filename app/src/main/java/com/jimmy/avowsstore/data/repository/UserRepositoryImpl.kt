package com.jimmy.avowsstore.data.repository

import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Empty
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.core.network.constructRoute
import com.jimmy.avowsstore.core.network.safeCall
import com.jimmy.avowsstore.data.remote.dto.ProductDto
import com.jimmy.avowsstore.data.remote.dto.UserDto
import com.jimmy.avowsstore.domain.mappers.toProduct
import com.jimmy.avowsstore.domain.mappers.toUser
import com.jimmy.avowsstore.domain.model.User
import com.jimmy.avowsstore.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class UserRepositoryImpl(
    private val client: HttpClient,
): UserRepository {


    override suspend fun getUserById(id: Int): Result<User, DataError> {
        val result = safeCall<UserDto> {
            client.get(
                urlString = constructRoute("/users/${id}")
            ) {
            }
        }
        when(result) {
            is Result.Error -> {
                return Result.Error(result.error)
            }
            is Result.Success -> {
                return Result.Success(result.data.toUser() )
            }
        }
    }
}