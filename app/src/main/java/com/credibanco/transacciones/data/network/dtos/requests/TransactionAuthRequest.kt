package com.credibanco.transacciones.data.network.dtos.requests

import kotlinx.serialization.Serializable

@Serializable
data class TransactionAuthRequest(
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String
)

