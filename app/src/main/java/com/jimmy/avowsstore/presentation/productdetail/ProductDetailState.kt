package com.jimmy.avowsstore.presentation.productdetail

import com.jimmy.avowsstore.core.data.UiText
import com.jimmy.avowsstore.domain.model.Product

data class ProductDetailState(
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val productId : Int? = null,
    val product: Product? = null,
    val isLoadingAddToCart: Boolean = false,
    val isLoadingCartCount: Boolean = false,
    val errorCartCount: UiText? = null,
    val cartCount: Int = 0,
    val isPullToRefresh: Boolean = false
)