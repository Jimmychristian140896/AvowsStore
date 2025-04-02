package com.jimmy.avowsstore.domain.repository

import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.data.remote.dto.LoginDto
import com.jimmy.avowsstore.domain.model.Product
import com.jimmy.avowsstore.domain.model.ProductCategory

interface ProductRepository {
    suspend fun getCategories(): Result<List<ProductCategory>, DataError>
    suspend fun getAllProducts(): Result<List<Product>, DataError>
    suspend fun getProductsByCategory(category: String): Result<List<Product>, DataError>
    suspend fun getProductById(id: Int): Result<Product, DataError>

}