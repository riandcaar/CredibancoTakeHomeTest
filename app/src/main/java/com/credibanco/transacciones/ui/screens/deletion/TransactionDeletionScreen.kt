package com.credibanco.transacciones.ui.screens.deletion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.credibanco.transacciones.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDeletion(
    transactionDeletionViewModel: TransactionDeletionViewModel = hiltViewModel(),
    navController: NavController
) {


    var receiptId by remember { mutableStateOf("") }
    var rrn by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text("Transaction Deletion") }
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = receiptId,
                        onValueChange = { receiptId = it },
                        label = { Text("Receipt ID") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = rrn,
                        onValueChange = { rrn = it },
                        label = { Text("RRN") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        transactionDeletionViewModel.annulTransaction(receiptId, rrn) { result ->
                            result.onSuccess { response ->
                                resultMessage =
                                    "Transaction annulled successfully: ${response.statusDescription}"
                                receiptId = ""
                                rrn = ""
                            }.onFailure { e ->
                                resultMessage = "Error: ${e.message}"
                            }
                        }
                    }) {
                        Text("Annul Transaction")
                    }
                    if (resultMessage.isNotEmpty()) {
                        Text(resultMessage)
                    }
                }
            }
        }
    )
}