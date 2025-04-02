package com.jimmy.avowsstore.presentation.products

import com.jimmy.avowsstore.core.data.UiText
import com.jimmy.avowsstore.domain.model.Product
import com.jimmy.avowsstore.domain.model.ProductCategory

data class ProductsState(
    val isLoading: Boolean = false,
    val isLoadingCategories: Boolean = false,
    val isLoadingProducts: Boolean = false,
    val errorCategories: UiText? = null,
    val errorProducts: UiText? = null,
    val categories: List<ProductCategory> = emptyList(),
    val products: List<Product> = emptyList(),
    val selectedCategory: ProductCategory? = null,
    val showProfileDialog: Boolean = false
)