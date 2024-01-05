package com.credibanco.transacciones.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.credibanco.transacciones.domain.MainRepositoryInterface
import com.credibanco.transacciones.ui.models.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionAuthViewModel @Inject constructor(
    private val mainRepository: MainRepositoryInterface
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionAuthViewState())
    val uiState = _uiState.asStateFlow()

    fun authorizeTransaction(
        amountInput: String,
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            try {
                val randomId = (0..1000).random().toString()
                val result = mainRepository.authorizeTransaction(
                    Transaction(
                        id = randomId,
                        amount = amountInput,
                    )
                )
                if (result.isSuccess) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            transactionResult = result.getOrThrow()
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = result.exceptionOrNull()?.message
                                ?: "Algo pasó con la red pero no sabemos que",
                        )
                    }
                }

            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
}