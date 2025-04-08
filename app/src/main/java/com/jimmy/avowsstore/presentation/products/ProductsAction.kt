package com.jimmy.avowsstore.presentation.products

import com.jimmy.avowsstore.domain.model.Product
import com.jimmy.avowsstore.domain.model.ProductCategory

sealed interface ProductsAction {
    data class SetSelectedCategory(val category: ProductCategory) : ProductsAction
    data class ProductClicked(val product: Product) : ProductsAction
    data object CartClicked : ProductsAction
    data object ProfileClicked : ProductsAction
    data object DismissProfileDialog : ProductsAction
    data object Logout : ProductsAction
    data object GetCategories : ProductsAction
    data object GetProducts : ProductsAction
    data object OnTryAgain : ProductsAction
    data object OnPullToRefresh : ProductsAction
}