package com.jimmy.avowsstore.domain.repository

import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.data.remote.dto.LoginDto
import com.jimmy.avowsstore.domain.model.Cart

interface CartRepository {
    suspend fun getCart(): Result<Cart, DataError>
    suspend fun getCartCount(): Result<Int, DataError>
    suspend fun addProductToCart(productId: Int, quantity: Int = 1): Result<Boolean, DataError>
}