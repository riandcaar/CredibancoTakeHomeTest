package com.credibanco.transacciones.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.credibanco.transacciones.data.local.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransaction(transaction: TransactionEntity): Long

    @Query("SELECT * FROM transactions")
    fun getTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE receipt = :receipt")
    suspend fun getTransactionByReceipt(receipt: String): List<TransactionEntity>


    @Delete()
    suspend fun deleteTransaction(items: List<TransactionEntity>)
}