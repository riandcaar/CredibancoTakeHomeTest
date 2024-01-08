package com.credibanco.transacciones.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String,
    val receipt: String = "",
    val annulmentCode: String = "",
    var status: String = "",
    var isAnnulmentButtonEnabled: Boolean = true
)