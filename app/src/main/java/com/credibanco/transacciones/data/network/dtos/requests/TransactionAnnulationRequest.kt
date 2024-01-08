package com.credibanco.transacciones.data.network.dtos.requests

import kotlinx.serialization.Serializable

@Serializable
data class TransactionAnnulationRequest(
    val receiptId: String,
    val rrn: String
)
