package com.jimmy.avowsstore.domain.model

data class Cart(
    val date: String,
    val id: Int,
    val products: List<CartProduct>,
)
