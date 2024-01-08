package com.credibanco.transacciones.data.network

import com.credibanco.transacciones.data.network.dtos.requests.TransactionAnnulationRequest
import com.credibanco.transacciones.data.network.dtos.requests.TransactionAuthRequest
import com.credibanco.transacciones.data.network.dtos.responses.TransactionAnnulationResponse
import com.credibanco.transacciones.data.network.dtos.responses.TransactionAuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface TransactionsApi {


    @POST("authorization")
    suspend fun authorizeTransaction(
        @Body transactionAuthRequest: TransactionAuthRequest,
        @Header("Authorization") authHeader: String
    ): Response<TransactionAuthResponse>

    @POST("annulment")
    suspend fun annulTransaction(
        @Body annulationRequest: TransactionAnnulationRequest,
        @Header("Authorization") authHeader: String
    ): Response<TransactionAnnulationResponse>

}

