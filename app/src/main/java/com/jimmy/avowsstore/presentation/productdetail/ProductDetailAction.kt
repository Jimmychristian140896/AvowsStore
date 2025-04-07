package com.jimmy.avowsstore.presentation.productdetail

sealed interface ProductDetailAction {
    data object NavigateBack: ProductDetailAction
    data object OnAddToCart: ProductDetailAction
    data object CartClicked: ProductDetailAction
    data object OnTryAgain: ProductDetailAction
}