package com.credibanco.transacciones.ui.screens.list

import com.credibanco.transacciones.data.local.entities.TransactionEntity

data class TransactionListViewState (
    val transactions: List<TransactionEntity>? = null,
    val error: String? = null,
    val loading: Boolean = false
)
