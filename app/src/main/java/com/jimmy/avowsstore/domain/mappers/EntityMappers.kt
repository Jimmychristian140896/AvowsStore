package com.jimmy.avowsstore.domain.mappers

import com.jimmy.avowsstore.data.local.entity.TransactionProductEntity
import com.jimmy.avowsstore.data.local.joinentity.TransactionWithProduct
import com.jimmy.avowsstore.domain.model.Transaction
import com.jimmy.avowsstore.domain.model.TransactionProduct
import com.jimmy.avowsstore.domain.model.User

fun TransactionProductEntity.toTransactionProduct() = TransactionProduct(
    id = id,
    quantity = quantity,
    name = name,
    price = price,
    imageUrl = imageUrl,
)


fun TransactionWithProduct.toTransaction(): Transaction {
    val transaction = Transaction(
        id = transaction.id,
        date = transaction.date,
        products = products.map { it.toTransactionProduct() }

    )
    return transaction
}