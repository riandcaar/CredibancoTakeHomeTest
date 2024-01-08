package com.credibanco.transacciones.domain.mappers

import com.credibanco.transacciones.data.local.entities.TransactionEntity
import com.credibanco.transacciones.data.network.dtos.requests.TransactionAuthRequest
import com.credibanco.transacciones.data.network.dtos.responses.TransactionAuthResponse
import com.credibanco.transacciones.ui.models.Transaction
import com.credibanco.transacciones.ui.models.TransactionResult

fun Transaction.toTransactionAuthRequest(): TransactionAuthRequest {
    return TransactionAuthRequest(id, commerceCode, terminalCode, amount, card)
}

fun TransactionAuthResponse.toTransactionResult(): TransactionResult {
    return TransactionResult(
        receipt = receiptId,
        annulmentCode = rrn,
        status = statusCode
    )
}


