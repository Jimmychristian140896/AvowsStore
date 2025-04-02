package com.jimmy.avowsstore.presentation.products

import com.jimmy.avowsstore.domain.model.Product
import com.jimmy.avowsstore.domain.model.ProductCategory

sealed interface ProductsAction {
    data class SetSelectedCategory(val category: ProductCategory) : ProductsAction
    data class ProductClicked(val product: Product) : ProductsAction
    data object ProfileClicked : ProductsAction
    data object DismissProfileDialog : ProductsAction
}