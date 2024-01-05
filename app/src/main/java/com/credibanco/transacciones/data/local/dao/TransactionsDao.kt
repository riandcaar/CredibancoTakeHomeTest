package com.credibanco.transacciones.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.credibanco.transacciones.data.local.entities.TransactionEntity

@Dao
interface TransactionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransaction(transaction: TransactionEntity): Long

    @Query("SELECT * FROM transactions")
    fun getTransactions(): List<TransactionEntity>

    @Delete()
    suspend fun deleteTransaction(items: List<TransactionEntity>)
}