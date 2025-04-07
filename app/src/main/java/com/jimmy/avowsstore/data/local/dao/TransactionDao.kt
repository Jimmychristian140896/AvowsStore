package com.jimmy.avowsstore.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jimmy.avowsstore.data.local.entity.TransactionEntity
import com.jimmy.avowsstore.data.local.entity.TransactionProductEntity
import com.jimmy.avowsstore.data.local.joinentity.TransactionWithProduct

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: String): TransactionWithProduct?


    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity): Long

    @Insert
    suspend fun insertProduct(product: TransactionProductEntity): Long

    @Insert
    suspend fun insertProducts(products: List<TransactionProductEntity>): List<Long>
}