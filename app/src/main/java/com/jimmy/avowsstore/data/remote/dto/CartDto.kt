package com.jimmy.avowsstore.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    @SerialName("date")
    val date: String,
    @SerialName("id")
    val id: Int,
    @SerialName("products")
    val products: List<Product>,
    @SerialName("userId")
    val userId: Int,
    @SerialName("__v")
    val v: Int
) {
    @Serializable
    data class Product(
        @SerialName("productId")
        val productId: Int,
        @SerialName("quantity")
        val quantity: Int
    )
}