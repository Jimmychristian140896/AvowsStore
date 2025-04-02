package com.jimmy.avowsstore.domain.model

class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val rating: Double,
    val ratingCount: Int,
    val category: String

)