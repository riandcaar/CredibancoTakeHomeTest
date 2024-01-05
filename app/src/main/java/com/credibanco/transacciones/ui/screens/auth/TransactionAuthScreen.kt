package com.credibanco.transacciones.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TransactionAuthScreen(transactionAuthViewModel: TransactionAuthViewModel = hiltViewModel()) {

    val uiState by transactionAuthViewModel.uiState.collectAsState()


    var idInput by remember { mutableStateOf("") }
    var commerceCodeInput by remember { mutableStateOf("") }
    var terminalCodeInput by remember { mutableStateOf("") }
    var amountInput by remember { mutableStateOf("") }
    var cardInput by remember { mutableStateOf("") }

    when {
        uiState.loading -> {
            Text("mostrar algo para loading")
        }

        uiState.transactionResult.status ==  "Aprobada"-> {
            Text("mostrar puede ser el ID de la transaccion ${uiState.transactionResult.receipt}")
        }

        uiState.transactionResult.status ==  "Denegada" -> {
            Text("mostrar algun mensaje de error y el id ${uiState.transactionResult.receipt} ")
        }

        else -> {
            Column() {
                Text("id")
                TextField(
                    value = idInput,
                    onValueChange = { idInput = it },
                )
                Text("commerceCode")
                TextField(
                    value = commerceCodeInput,
                    onValueChange = { commerceCodeInput = it },
                )
                Text("terminalCode")
                TextField(
                    value = terminalCodeInput,
                    onValueChange = { terminalCodeInput = it },
                )
                Text("amount")
                TextField(
                    value = amountInput,
                    onValueChange = { amountInput = it },
                )
                Text("card")
                TextField(
                    value = cardInput,
                    onValueChange = { cardInput = it },
                )

                Button(onClick = {
                    transactionAuthViewModel.authorizeTransaction(
                        amountInput,
                    )
                }) {
                    Text("Validar transacción")
                }
            }
        }
    }

}