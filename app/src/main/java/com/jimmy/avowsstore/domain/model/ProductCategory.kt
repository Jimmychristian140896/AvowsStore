package com.jimmy.avowsstore.domain.model

data class ProductCategory(
    val id: String,
    val name: String

)

val ProductCategoryAll = ProductCategory(id = "", name = "all")