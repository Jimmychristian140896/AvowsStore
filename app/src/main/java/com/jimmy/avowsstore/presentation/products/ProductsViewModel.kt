package com.jimmy.avowsstore.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimmy.avowsstore.core.data.asUiText
import com.jimmy.avowsstore.core.data.onFailure
import com.jimmy.avowsstore.core.data.onSuccess
import com.jimmy.avowsstore.domain.model.ProductCategory
import com.jimmy.avowsstore.domain.model.ProductCategoryAll
import com.jimmy.avowsstore.domain.repository.CartRepository
import com.jimmy.avowsstore.domain.repository.ProductRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository

) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(ProductsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
                getCategories()
                getProducts()
                getCartCount()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ProductsState()
        )

    private val _eventChannel = Channel<ProductsEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: ProductsAction) {
        when (action) {
            is ProductsAction.SetSelectedCategory -> {
                _state.update { it.copy(
                    selectedCategory = action.category
                ) }
                getProducts()
            }

            is ProductsAction.ProductClicked -> {
                viewModelScope.launch {
                    _eventChannel.send(ProductsEvent.ProductClicked(action.product))
                }
            }

            is ProductsAction.ProfileClicked -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        showProfileDialog = true
                    ) }
                    //_eventChannel.send(ProductsEvent.ProfileClicked)
                }
            }

            ProductsAction.DismissProfileDialog -> {
                _state.update { it.copy(
                    showProfileDialog = false
                ) }
            }

            ProductsAction.CartClicked -> {
                viewModelScope.launch {
                    _eventChannel.send(ProductsEvent.CartClicked)
                }
            }
            else -> TODO("Handle actions")
        }
    }

    private fun getCategories(){
        viewModelScope.launch {
            _state.update { it.copy(
                isLoadingCategories = true
            ) }
            productRepository.getCategories()
                .onSuccess { categories ->
                    val allCategory = ProductCategoryAll
                    _state.update { it.copy(
                        isLoadingCategories = false,
                        categories = categories.toMutableList().apply {
                            add(0, allCategory)
                        },
                        selectedCategory = allCategory
                    ) }
                }
                .onFailure { error ->
                    _state.update { it.copy(
                        isLoadingCategories = false,
                        errorCategories = error.asUiText()
                    ) }
                }
        }
    }

    private fun getProducts(){
        viewModelScope.launch {
            _state.update { it.copy(
                isLoadingProducts = true
            ) }
            if(_state.value.selectedCategory == null || _state.value.selectedCategory == ProductCategoryAll) {
                productRepository.getAllProducts()
            } else {
                productRepository.getProductsByCategory(_state.value.selectedCategory!!.id)
            }.onSuccess { products ->
                _state.update { it.copy(
                    isLoadingProducts = false,
                    products = products
                ) }
            }.onFailure {
                error ->
                _state.update { it.copy(
                    isLoadingProducts = false,
                    errorProducts = error.asUiText()
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