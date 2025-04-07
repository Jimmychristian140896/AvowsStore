package com.jimmy.avowsstore.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transaction_products",
    primaryKeys = [
        "id",
        "transactionId"
    ],
    foreignKeys = [ForeignKey(
        entity = TransactionEntity::class,
        parentColumns = ["id"],
        childColumns = ["transactionId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class TransactionProductEntity(
    val id: Int,
    val transactionId: String,
    val quantity: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
)