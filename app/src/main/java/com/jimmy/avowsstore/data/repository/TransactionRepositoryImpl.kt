package com.jimmy.avowsstore.data.repository

import com.jimmy.avowsstore.core.data.Constant
import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.core.network.constructRoute
import com.jimmy.avowsstore.core.network.safeCall
import com.jimmy.avowsstore.data.remote.dto.CartDto
import com.jimmy.avowsstore.data.remote.dto.ProductDto
import com.jimmy.avowsstore.domain.mappers.toCart
import com.jimmy.avowsstore.domain.mappers.toTransaction
import com.jimmy.avowsstore.domain.model.Cart
import com.jimmy.avowsstore.domain.model.Transaction
import com.jimmy.avowsstore.domain.repository.CartRepository
import com.jimmy.avowsstore.domain.repository.TransactionRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class TransactionRepositoryImpl(
    private val client: HttpClient
): TransactionRepository {
    override suspend fun getTransactionById(id: String): Result<Transaction, DataError> {
        val resultCart = safeCall<CartDto> {
            client.get(
                urlString = constructRoute("/carts/${Constant.CART_ID}")
            ) {
            }
        }
        val resultProduct = safeCall<List<ProductDto>> {
            client.get(
                urlString = constructRoute("/products")
            ) {
            }
        }
        when {
            resultCart is Result.Success && resultProduct is Result.Success -> {
                return Result.Success(resultCart.data.toTransaction(resultProduct.data))
            }
            resultCart is Result.Error -> {
                return Result.Error(resultCart.error)
            }
            resultProduct is Result.Error -> {
                return Result.Error(resultProduct.error)
            }
            else -> {
                return Result.Error(DataError.HttpError.UNKNOWN)
            }

        }
    }

}