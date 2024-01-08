package com.credibanco.transacciones.ui.screens.deletion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.credibanco.transacciones.data.network.dtos.responses.TransactionAnnulationResponse
import com.credibanco.transacciones.domain.MainRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDeletionViewModel @Inject constructor (
    private val mainRepository: MainRepositoryInterface
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionDeletionViewState())
    val uiState = _uiState.asStateFlow()

    fun annulTransaction(receiptId: String, rrn: String, onResult: (Result<TransactionAnnulationResponse>) -> Unit) {
        viewModelScope.launch {
            val result = mainRepository.annulTransaction(receiptId, rrn)
            onResult(result)
        }
    }
}