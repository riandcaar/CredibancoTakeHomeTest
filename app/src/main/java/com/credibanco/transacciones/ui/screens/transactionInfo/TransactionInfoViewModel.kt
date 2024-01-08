package com.credibanco.transacciones.ui.screens.transactionInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.credibanco.transacciones.data.local.dao.TransactionsDao
import com.credibanco.transacciones.domain.MainRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionInfoViewModel @Inject constructor(
    private val mainRepository: MainRepositoryInterface,
    private val transactionsDao: TransactionsDao
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionInfoViewState())
    val uiState = _uiState.asStateFlow()

    fun loadTransactionDetail(receipt: String) {
        viewModelScope.launch {
            try {
                val transaction = transactionsDao.getTransactionByReceipt(receipt)
                _uiState.value = TransactionInfoViewState(transactions = transaction)
            } catch (e: Exception) {
                _uiState.value = TransactionInfoViewState(error = e.message)
            }
        }
    }

    fun annulTransaction(
        receiptId: String,
        rrn: String,
    ) {
        viewModelScope.launch {
            val result = mainRepository.annulTransaction(receiptId, rrn)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(annulmentSuccessful = true)
                loadTransactionDetail(receiptId)
            }
        }

    }

}
