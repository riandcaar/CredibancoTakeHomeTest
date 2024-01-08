package com.credibanco.transacciones.data.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.credibanco.transacciones.data.network.dtos.requests.TransactionAnnulationRequest
import java.util.Base64
import com.credibanco.transacciones.data.network.dtos.requests.TransactionAuthRequest
import com.credibanco.transacciones.data.network.dtos.responses.TransactionAnnulationResponse
import com.credibanco.transacciones.data.network.dtos.responses.TransactionAuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

val header = "000123000ABC"
@RequiresApi(Build.VERSION_CODES.O)
val encoded = Base64.getEncoder().encodeToString(header.toByteArray())
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

