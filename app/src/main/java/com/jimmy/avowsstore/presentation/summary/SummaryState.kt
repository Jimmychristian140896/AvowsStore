package com.jimmy.avowsstore.presentation.summary

import com.jimmy.avowsstore.core.data.UiText
import com.jimmy.avowsstore.domain.model.Cart
import com.jimmy.avowsstore.domain.model.Transaction

data class SummaryState(

    val isLoading: Boolean = false,
    val transaction: Transaction? = null,
    val error: UiText? = null
)