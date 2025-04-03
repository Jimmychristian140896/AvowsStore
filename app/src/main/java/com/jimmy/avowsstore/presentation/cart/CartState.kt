package com.jimmy.avowsstore.presentation.cart

import com.jimmy.avowsstore.core.data.UiText
import com.jimmy.avowsstore.domain.model.Cart

data class CartState(
    val isLoading: Boolean = false,
    val cart: Cart? = null,
    val error: UiText? = null

)