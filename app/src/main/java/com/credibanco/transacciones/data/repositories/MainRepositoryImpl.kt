package com.credibanco.transacciones.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.credibanco.transacciones.data.local.dao.TransactionsDao
import com.credibanco.transacciones.data.local.entities.TransactionEntity
import com.credibanco.transacciones.data.network.TransactionsApi
import com.credibanco.transacciones.data.network.dtos.requests.TransactionAnnulationRequest
import com.credibanco.transacciones.data.network.dtos.responses.TransactionAnnulationResponse
import com.credibanco.transacciones.domain.MainRepositoryInterface
import com.credibanco.transacciones.domain.mappers.toTransactionAuthRequest
import com.credibanco.transacciones.domain.mappers.toTransactionResult
import com.credibanco.transacciones.ui.models.Transaction
import com.credibanco.transacciones.ui.models.TransactionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Base64
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val transactionsApi: TransactionsApi,
    private val transactionsDao: TransactionsDao
) : MainRepositoryInterface {

    private val commerceCode = "000123"
    private val terminalCode = "000ABC"

    @RequiresApi(Build.VERSION_CODES.O)
    fun String.encodeToBase64(): String {
        return Base64.getEncoder().encodeToString(this.toByteArray())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun authorizeTransaction(transaction: Transaction): Result<TransactionResult> {
        return withContext(Dispatchers.IO) {
            try {
                val authHeader =
                    "Basic ${"${transaction.commerceCode}${transaction.terminalCode}".encodeToBase64()}"

                val response = transactionsApi.authorizeTransaction(
                    transaction.toTransactionAuthRequest(),
                    authHeader
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.statusDescription == "Aprobada") {
                            val transactionEntity = TransactionEntity(
                                id = transaction.id,
                                commerceCode = transaction.commerceCode,
                                terminalCode = transaction.terminalCode,
                                amount = transaction.amount,
                                card = transaction.card,
                                receipt = it.receiptId,
                                annulmentCode = it.rrn,
                                status = it.statusDescription
                            )
                            transactionsDao.saveTransaction(transactionEntity)
                        }
                        return@withContext Result.success(it.toTransactionResult())
                    }
                }
                Result.failure(Exception("Network request failed with code: ${response.code()}"))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun annulTransaction(
        transactionId: String,
        rrn: String
    ): Result<TransactionAnnulationResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val authHeader =
                    "Basic ${"$commerceCode$terminalCode".encodeToBase64()}"

                val response = transactionsApi.annulTransaction(
                    TransactionAnnulationRequest(
                        transactionId,
                        rrn
                    ), authHeader
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        updateLocalTransactionStatus(transactionId, "Annulled")
                        return@withContext Result.success(it)
                    }
                }
                Result.failure(Exception("Network request failed with code: ${response.code()}"))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private suspend fun updateLocalTransactionStatus(transactionId: String, newStatus: String) {
        transactionsDao.getTransactionByReceipt(transactionId).firstOrNull()?.let { transaction ->
            val updatedTransaction = transaction.copy(status = newStatus)
            transactionsDao.saveTransaction(updatedTransaction)
        }
    }
}