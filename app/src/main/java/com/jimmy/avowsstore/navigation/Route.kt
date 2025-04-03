package com.jimmy.avowsstore.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Auth: Route()
    @Serializable
    data object Login: Route()
    @Serializable
    data object Main: Route()
    @Serializable
    data object Products: Route()
    @Serializable
    data class ProductDetail(val id: Int): Route()
    @Serializable
    data object Cart: Route()
    @Serializable
    data class Summary(val id: String): Route()
    @Serializable
    data object Profile: Route()
}