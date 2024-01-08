package com.credibanco.transacciones.data.network.dtos.responses

import kotlinx.serialization.Serializable

@Serializable
data class TransactionAnnulationResponse(
    val statusCode: String,
    val statusDescription: String
)
