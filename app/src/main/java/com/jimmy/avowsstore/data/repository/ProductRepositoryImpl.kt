package com.jimmy.avowsstore.data.repository

import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.core.network.constructRoute
import com.jimmy.avowsstore.core.network.safeCall
import com.jimmy.avowsstore.data.remote.dto.LoginDto
import com.jimmy.avowsstore.data.remote.dto.ProductDto
import com.jimmy.avowsstore.domain.mappers.toProduct
import com.jimmy.avowsstore.domain.model.Product
import com.jimmy.avowsstore.domain.model.ProductCategory
import com.jimmy.avowsstore.domain.repository.ProductRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ProductRepositoryImpl(
    private val client: HttpClient

): ProductRepository {
    override suspend fun getCategories(): Result<List<ProductCategory>, DataError> {
        val result = safeCall<List<String>> {
            client.get(
                urlString = constructRoute("/products/categories")
            ) {
            }
        }
        when(result) {
            is Result.Error -> {
                return Result.Error(result.error)
            }
            is Result.Success -> {
                return Result.Success(result.data.map { ProductCategory(it, it) })
            }
        }
    }

    override suspend fun getAllProducts(): Result<List<Product>, DataError> {
        val result = safeCall<List<ProductDto>> {
            client.get(
                urlString = constructRoute("/products")
            ) {
            }
        }
        when(result) {
            is Result.Error -> {
                return Result.Error(result.error)
            }
            is Result.Success -> {
                return Result.Success(result.data.map { it.toProduct() })
            }
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>, DataError> {
        val result = safeCall<List<ProductDto>> {
            client.get(
                urlString = constructRoute("/products/category/${category}")
            ) {
                parameter("sort","asc")
            }
        }
        when(result) {
            is Result.Error -> {
                return Result.Error(result.error)
            }
            is Result.Success -> {
                return Result.Success(result.data.map { it.toProduct() })
            }
        }
    }

    override suspend fun getProductById(id: Int): Result<Product, DataError> {
        val result = safeCall<ProductDto> {
            client.get(
                urlString = constructRoute("/products/${id}")
            ) {
            }
        }
        when(result) {
            is Result.Error -> {
                return Result.Error(result.error)
            }
            is Result.Success -> {
                return Result.Success(result.data.toProduct() )
            }
        }
    }
}