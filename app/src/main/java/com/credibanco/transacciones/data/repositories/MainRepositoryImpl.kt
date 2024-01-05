package com.credibanco.transacciones.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.credibanco.transacciones.data.local.CredibancoDatabase
import com.credibanco.transacciones.data.local.dao.TransactionsDao
import com.credibanco.transacciones.data.network.TransactionsApi
import com.credibanco.transacciones.domain.MainRepositoryInterface
import com.credibanco.transacciones.domain.mappers.toTransactionAuthRequest
import com.credibanco.transacciones.domain.mappers.toTransactionEntity
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun String.encodeToBase64(): String {
        return Base64.getEncoder().encodeToString(this.toByteArray())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun authorizeTransaction(transaction: Transaction): Result<TransactionResult> {
        return withContext(Dispatchers.IO) {
            try {
                val authHeader = "Basic ${"${transaction.commerceCode}${transaction.terminalCode}".encodeToBase64()}"

                val response = transactionsApi.authorizeTransaction(transaction.toTransactionAuthRequest(), authHeader)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.statusDescription == "Aprobada"){
                           transactionsDao.saveTransaction(transaction.toTransactionEntity())
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
}