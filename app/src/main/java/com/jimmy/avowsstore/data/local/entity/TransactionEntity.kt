package com.jimmy.avowsstore.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions"
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val date: String,

)