package com.jimmy.avowsstore.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimmy.avowsstore.core.data.asUiText
import com.jimmy.avowsstore.core.data.onFailure
import com.jimmy.avowsstore.core.data.onSuccess
import com.jimmy.avowsstore.domain.repository.CartRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(CartState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
                getCart()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CartState()
        )

    private val _eventChannel = Channel<CartEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: CartAction) {
        when (action) {
            /*is CartAction.OnProductCheckedChange -> {
                viewModelScope.launch {
                    val products = state.value.cart?.products?.map {
                        if (it.id == action.cartProduct.id) {
                            it.copy(
                                isChecked = !it.isChecked
                            )
                        } else {
                            it
                        }
                    } ?: emptyList()

                    _state.update {
                        it.copy(
                            cart = it.cart?.copy(
                                products = products
                            )
                        )
                    }
                }
            }*/

            is CartAction.OnProductDelete -> {
                /*val products = state.value.cart?.products?.toMutableList()
                    ?.apply { remove(action.cartProduct) }?.toList() ?: emptyList()*/
                val products = state.value.cart?.products?.mapNotNull {
                    if (it.id != action.cartProduct.id) {
                        it
                    } else {
                        null
                    }
                } ?: emptyList()

                _state.update {
                    it.copy(
                        cart = it.cart?.copy(
                            products = products
                        )
                    )
                }
            }

            is CartAction.OnProductQuantityMinus -> {
                val products = state.value.cart?.products?.map {
                    if (it.id == action.cartProduct.id) {
                        it.copy(
                            quantity = it.quantity - 1
                        )
                    } else {
                        it
                    }
                } ?: emptyList()

                _state.update {
                    it.copy(
                        cart = it.cart?.copy(
                            products = products
                        )
                    )
                }
            }

            is CartAction.OnProductQuantityPlus -> {
                val products = state.value.cart?.products?.map {
                    if (it.id == action.cartProduct.id) {
                        it.copy(
                            quantity = it.quantity + 1
                        )
                    } else {
                        it
                    }
                } ?: emptyList()

                _state.update {
                    it.copy(
                        cart = it.cart?.copy(
                            products = products
                        )
                    )
                }
            }

            CartAction.OnCheckout -> {
                viewModelScope.launch {
                    _eventChannel.send(CartEvent.OnCheckout("1"))
                }
            }

            CartAction.NavigateBack -> {
                viewModelScope.launch {
                    _eventChannel.send(CartEvent.NavigateBack)
                }
            }
            else -> TODO("Handle actions")
        }
    }

    private fun getCart() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            cartRepository.getCart()
                .onSuccess { cart ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            cart = cart
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.asUiText()
                        )
                    }
                }
        }
    }
}