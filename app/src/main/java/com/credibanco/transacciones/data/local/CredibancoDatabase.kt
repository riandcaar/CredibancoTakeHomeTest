package com.credibanco.transacciones.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.credibanco.transacciones.data.local.dao.TransactionsDao
import com.credibanco.transacciones.data.local.entities.TransactionEntity


@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false)
abstract class CredibancoDatabase : RoomDatabase() {
    abstract fun transactionsDao(): TransactionsDao
}