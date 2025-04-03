package com.jimmy.avowsstore.domain.repository

import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.domain.model.Cart
import com.jimmy.avowsstore.domain.model.Transaction

interface TransactionRepository {
    suspend fun getTransactionById(id: String): Result<Transaction, DataError>

}