package com.credibanco.transacciones.ui.screens.transactionInfo

import com.credibanco.transacciones.data.local.entities.TransactionEntity

data class TransactionInfoViewState (
    val transactions: List<TransactionEntity>? = null,
    val error: String? = null,
    val loading: Boolean = false,
    val annulmentSuccessful: Boolean = false
)