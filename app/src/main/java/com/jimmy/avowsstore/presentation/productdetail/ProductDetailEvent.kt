package com.jimmy.avowsstore.presentation.productdetail

import com.jimmy.avowsstore.core.data.UiText

sealed interface ProductDetailEvent {
    data object NavigateBack: ProductDetailEvent
    data object AddToCartSuccess: ProductDetailEvent
    data object CartClicked: ProductDetailEvent
    data class AddToCartFailed(val message: UiText): ProductDetailEvent

}