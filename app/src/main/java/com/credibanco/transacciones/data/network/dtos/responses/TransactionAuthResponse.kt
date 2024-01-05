package com.credibanco.transacciones.data.network.dtos.responses

import kotlinx.serialization.Serializable

@Serializable
data class TransactionAuthResponse(
    val receiptId: String,
    val rrn: String,
    val statusCode: String,
    val statusDescription: String,
)
