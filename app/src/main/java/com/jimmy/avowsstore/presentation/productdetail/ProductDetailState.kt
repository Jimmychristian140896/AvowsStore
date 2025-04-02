package com.jimmy.avowsstore.presentation.productdetail

import com.jimmy.avowsstore.core.data.UiText
import com.jimmy.avowsstore.domain.model.Product

data class ProductDetailState(
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val productId : Int? = null,
    val product: Product? = null,
    val isLoadingAddToCart: Boolean = false
)