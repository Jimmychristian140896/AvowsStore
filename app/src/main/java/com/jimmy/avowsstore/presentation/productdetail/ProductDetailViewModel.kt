package com.jimmy.avowsstore.presentation.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jimmy.avowsstore.core.data.asUiText
import com.jimmy.avowsstore.core.data.onFailure
import com.jimmy.avowsstore.core.data.onSuccess
import com.jimmy.avowsstore.domain.model.ProductCategoryAll
import com.jimmy.avowsstore.domain.repository.CartRepository
import com.jimmy.avowsstore.domain.repository.ProductRepository
import com.jimmy.avowsstore.navigation.Route
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route: Route.ProductDetail = savedStateHandle.toRoute()
    val id: Int = route.id

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(ProductDetailState(
        productId = id
    ))
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
                getProductById(id)
                getCartCount()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ProductDetailState(
                productId = id
            )
        )

    private val _eventChannel = Channel<ProductDetailEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: ProductDetailAction) {
        when (action) {
            ProductDetailAction.NavigateBack -> {
                viewModelScope.launch {
                    _eventChannel.send(ProductDetailEvent.NavigateBack)
                }
            }

            ProductDetailAction.OnAddToCart -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        isLoadingAddToCart = true
                    ) }
                    productRepository.getProductById(id)
                        .onSuccess { success ->
                            _state.update { it.copy(
                                isLoadingAddToCart = false,
                                cartCount = it.cartCount+1
                            ) }
                            _eventChannel.send(ProductDetailEvent.AddToCartSuccess)
                        }
                        .onFailure { error ->
                            _state.update { it.copy(
                                isLoadingAddToCart = false
                            ) }
                            _eventChannel.send(ProductDetailEvent.AddToCartFailed(error.asUiText()))

                        }
                }
            }

            ProductDetailAction.CartClicked -> {
                viewModelScope.launch {
                    _eventChannel.send(ProductDetailEvent.CartClicked)
                }
            }
            else -> TODO("Handle actions")
        }
    }

    private fun getProductById(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            productRepository.getProductById(id)
                .onSuccess { product ->
                    _state.update { it.copy(
                        isLoading = false,
                        product = product
                    ) }
                }
                .onFailure { error ->
                    _state.update { it.copy(
                        isLoading = false,
                        error = error.asUiText()
                    ) }
                }
        }
    }
    private fun getCartCount(){
        viewModelScope.launch {
            _state.update { it.copy(
                isLoadingCartCount = true
            ) }
            cartRepository.getCartCount()
                .onSuccess { cartCount ->
                    _state.update { it.copy(
                        isLoadingCartCount = false,
                        cartCount = cartCount
                    ) }
                }.onFailure {
                        error ->
                    _state.update { it.copy(
                        isLoadingCartCount = false,
                        errorCartCount = error.asUiText()
                    ) }
                }


        }

    }
}