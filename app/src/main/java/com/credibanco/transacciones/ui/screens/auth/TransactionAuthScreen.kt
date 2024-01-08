package com.credibanco.transacciones.ui.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.credibanco.transacciones.R
import com.credibanco.transacciones.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionAuthScreen(
    transactionAuthViewModel: TransactionAuthViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by transactionAuthViewModel.uiState.collectAsState()


    var idInput by remember { mutableStateOf("") }
    var commerceCodeInput by remember { mutableStateOf("") }
    var terminalCodeInput by remember { mutableStateOf("") }
    var amountInput by remember { mutableStateOf("") }
    var cardInput by remember { mutableStateOf("") }


    Scaffold(
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.TransactionList) },
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_transaction_list), contentDescription = "Transaction List")
                }
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.SearchTransaction) },
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search Transaction")
                }
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                when {
                    uiState.loading -> {
                        CircularProgressIndicator()
                    }

                    uiState.transactionResult.status == "Aprobada" -> {
                        Text("mostrar puede ser el ID de la transaccion ${uiState.transactionResult.receipt}")
                    }

                    uiState.transactionResult.status == "Denegada" -> {
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
        }
    )
}