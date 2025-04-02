package com.jimmy.avowsstore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.jimmy.avowsstore.presentation.cart.CartScreenRoot
import com.jimmy.avowsstore.presentation.login.LoginScreenRoot
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailScreenRoot
import com.jimmy.avowsstore.presentation.products.ProductsScreenRoot
import com.jimmy.avowsstore.presentation.summary.SummaryScreenRoot

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Route.Main
    ) {
        navigation<Route.Auth>(
            startDestination = Route.Login
        ) {
            composable<Route.Login> {
                LoginScreenRoot(
                    navHostController
                )
            }
        }
        navigation<Route.Main>(
            startDestination = Route.Products
        ) {
            composable<Route.Products> {
                ProductsScreenRoot(
                    navHostController
                )
            }
            composable<Route.ProductDetail> {
                ProductDetailScreenRoot(
                    navHostController
                )
            }
            composable<Route.Cart> {
                CartScreenRoot(
                    navHostController
                )
            }
            composable<Route.Summary> {
                SummaryScreenRoot(
                    navHostController
                )
            }/*
            composable<Route.Profile> {
                ProfileScreenRoot(
                    navHostController
                )
            }*/
        }
    }
}