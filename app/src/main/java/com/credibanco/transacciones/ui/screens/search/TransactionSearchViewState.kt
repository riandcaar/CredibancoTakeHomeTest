package com.credibanco.transacciones.ui.screens.search

import com.credibanco.transacciones.data.local.entities.TransactionEntity

data class TransactionSearchViewState (
    val transactions: List<TransactionEntity>? = null,
    val error: String? = null,
    val loading: Boolean = false
)
