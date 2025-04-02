package com.jimmy.avowsstore.di

import com.jimmy.avowsstore.presentation.cart.CartViewModel
import com.jimmy.avowsstore.presentation.login.LoginViewModel
import com.jimmy.avowsstore.presentation.productdetail.ProductDetailViewModel
import com.jimmy.avowsstore.presentation.products.ProductsViewModel
import com.jimmy.avowsstore.presentation.profile.ProfileViewModel
import com.jimmy.avowsstore.presentation.summary.SummaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::ProductsViewModel)
    viewModelOf(::ProductDetailViewModel)
    viewModelOf(::CartViewModel)
    viewModelOf(::SummaryViewModel)
    viewModelOf(::ProfileViewModel)

}