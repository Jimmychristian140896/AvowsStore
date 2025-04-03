package com.jimmy.avowsstore.domain.model

data class TransactionProduct(
    val id: Int,
    val quantity: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
)
