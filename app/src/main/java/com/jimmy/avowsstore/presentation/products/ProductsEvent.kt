package com.jimmy.avowsstore.presentation.products

import com.jimmy.avowsstore.domain.model.Product

sealed interface ProductsEvent {
    data class ProductClicked(val product: Product) : ProductsEvent
    data object ProfileClicked : ProductsEvent
    data object CartClicked : ProductsEvent


}