package com.credibanco.transacciones.ui.screens.search

import androidx.lifecycle.ViewModel
import com.credibanco.transacciones.data.local.dao.TransactionsDao
import com.credibanco.transacciones.domain.MainRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionSearchViewModel @Inject constructor(
    private val mainRepository: MainRepositoryInterface,
    private val transactionsDao: TransactionsDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionSearchViewState())
    val uiState = _uiState.asStateFlow()

}