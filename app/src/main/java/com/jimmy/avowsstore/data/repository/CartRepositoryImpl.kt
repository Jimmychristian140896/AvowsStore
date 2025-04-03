package com.jimmy.avowsstore.data.repository

import com.jimmy.avowsstore.core.data.Constant
import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Result
import com.jimmy.avowsstore.core.network.constructRoute
import com.jimmy.avowsstore.core.network.safeCall
import com.jimmy.avowsstore.data.remote.dto.CartDto
import com.jimmy.avowsstore.data.remote.dto.LoginDto
import com.jimmy.avowsstore.data.remote.dto.ProductDto
import com.jimmy.avowsstore.domain.mappers.toCart
import com.jimmy.avowsstore.domain.mappers.toLogin
import com.jimmy.avowsstore.domain.model.Cart
import com.jimmy.avowsstore.domain.repository.CartRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class CartRepositoryImpl(
    private val client: HttpClient

): CartRepository {
    override suspend fun getCart(): Result<Cart, DataError> {
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
                return Result.Success(resultCart.data.toCart(resultProduct.data))
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

    override suspend fun getCartCount(): Result<Int, DataError> {
        val result = safeCall<CartDto> {
            client.get(
                urlString = constructRoute("/carts/${Constant.CART_ID}")
            ) {
            }
        }
        when(result) {
            is Result.Error -> {
                return Result.Error(result.error)
            }
            is Result.Success -> {
                return Result.Success(result.data.products.sumOf { it.quantity })
            }
        }
    }

    override suspend fun addProductToCart(productId: Int, quantity: Int): Result<Boolean, DataError> {
        val resultCart = safeCall<CartDto> {
            client.get(
                urlString = constructRoute("/carts/${Constant.CART_ID}")
            ) {
            }
        }
        when(resultCart) {
            is Result.Success -> {
                var cart = resultCart.data
                cart = cart.copy(
                    products = cart.products.toMutableList().apply {
                        add(
                            CartDto.Product(
                                productId = productId,
                                quantity = quantity
                            )
                        )
                    }
                )
                val resultUpdateCart = safeCall<CartDto> {
                    client.put(
                        urlString = constructRoute("/carts/${Constant.CART_ID}")
                    ) {
                        setBody(cart)
                    }
                }
                return when(resultUpdateCart) {
                    is Result.Success -> {
                        Result.Success(true)
                    }
                    is Result.Error -> {
                        Result.Error(resultUpdateCart.error)
                    }
                }
            }
            is Result.Error -> {
                return Result.Error(resultCart.error)
            }
        }

    }
}