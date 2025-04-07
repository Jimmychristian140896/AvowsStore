package com.jimmy.avowsstore.presentation.cart

sealed interface CartEvent {
    data object NavigateBack: CartEvent
    data object OnStartShoppingClicked: CartEvent

    data class OnCheckout(val id: String) : CartEvent

}