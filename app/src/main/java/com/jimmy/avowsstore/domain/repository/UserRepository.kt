package com.jimmy.avowsstore.domain.repository

import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Empty
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.domain.model.User

interface UserRepository {
    /*suspend fun getUsers(): Result<List<User>, DataError>
    suspend fun upsertUser(user: User): Result<Empty, DataError>
    suspend fun deleteUser(user: User): Result<Empty, DataError>*/
    suspend fun getUserById(id: Int): Result<User, DataError>

}