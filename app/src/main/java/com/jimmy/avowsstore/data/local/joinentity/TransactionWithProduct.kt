package com.jimmy.avowsstore.data.local.joinentity

import androidx.room.Embedded
import androidx.room.Relation
import com.jimmy.avowsstore.data.local.entity.TransactionEntity
import com.jimmy.avowsstore.data.local.entity.TransactionProductEntity

data class TransactionWithProduct(
    @Embedded val transaction: TransactionEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "transactionId"
    )
    val products: List<TransactionProductEntity>
)