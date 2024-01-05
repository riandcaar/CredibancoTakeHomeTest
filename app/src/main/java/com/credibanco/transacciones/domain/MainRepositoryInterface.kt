package com.credibanco.transacciones.domain

import com.credibanco.transacciones.ui.models.Transaction
import com.credibanco.transacciones.ui.models.TransactionResult

interface MainRepositoryInterface {
   suspend fun authorizeTransaction(transaction: Transaction) :Result<TransactionResult>
}