package com.jimmy.avowsstore.presentation.cart

import com.jimmy.avowsstore.domain.model.CartProduct
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailAction

sealed interface CartAction {
    //data class OnProductCheckedChange(val cartProduct: CartProduct) : CartAction
    data object NavigateBack: CartAction

    data class OnProductQuantityPlus(val cartProduct: CartProduct) : CartAction
    data class OnProductQuantityMinus(val cartProduct: CartProduct) : CartAction
    data class OnProductDelete(val cartProduct: CartProduct) : CartAction
    data object OnCheckout : CartAction
    data object OnStartShoppingClicked : CartAction
    data object OnTryAgain : CartAction
}