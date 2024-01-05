package com.credibanco.transacciones.ui.screens.auth

import com.credibanco.transacciones.ui.models.TransactionResult

data class TransactionAuthViewState(
    val transactionResult: TransactionResult = TransactionResult(),
    val error: String? = null,
    val loading: Boolean = false,
)