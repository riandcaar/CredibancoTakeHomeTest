package com.credibanco.transacciones.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.credibanco.transacciones.data.local.dao.TransactionsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor (
    private val transactionsDao: TransactionsDao
) : ViewModel() {


    private val _uiState = MutableStateFlow(TransactionListViewState())
    val uiState = _uiState.asStateFlow()


    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            _uiState.value = TransactionListViewState(loading = true)
            try {
                transactionsDao.getTransactions().collect { transactions ->
                    _uiState.value =
                        TransactionListViewState(transactions = transactions, loading = false)
                }
            } catch (e: Exception) {
                _uiState.value = TransactionListViewState(error = e.message, loading = false)
            }
        }
    }
}