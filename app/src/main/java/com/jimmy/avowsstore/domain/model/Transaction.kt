package com.jimmy.avowsstore.domain.model

data class Transaction(
    val date: String,
    val id: String,
    val products: List<TransactionProduct>,
)