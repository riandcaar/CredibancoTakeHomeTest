package com.credibanco.transacciones.ui.screens.transactionInfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.credibanco.transacciones.R
import com.credibanco.transacciones.ui.components.CardTransactionInfoComplete

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionInfoScreen(
    transactionInfoViewModel: TransactionInfoViewModel,
    navController: NavController,
    receipt: String
) {
    LaunchedEffect(receipt) {
        transactionInfoViewModel.loadTransactionDetail(receipt)
    }

    val uiState by transactionInfoViewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = colorResource(R.color.credibanco)
            ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = (stringResource(id = R.string.back)),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(Modifier.weight(0.4f))
                        Text(
                            (stringResource(id = R.string.transaction_information)),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.weight(1f))
                    }
                }
            )
        },

        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding() + 15.dp)
            ) {
                when {
                    uiState.loading -> {
                        CircularProgressIndicator()
                    }

                    uiState.error != null -> {
                        Text(text = "Error: ${uiState.error}")
                    }

                    uiState.transactions.isNullOrEmpty() -> {
                        Text(stringResource(id = R.string.no_transactions_vailable))
                    }

                    else -> {
                        LazyColumn {
                            uiState.transactions?.let { transactions ->
                                items(transactions.size) { index ->
                                    val transaction = transactions[index]
                                    CardTransactionInfoComplete(transaction)
                                    Spacer(modifier = Modifier.height(30.dp))
                                    Box(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Button(
                                            onClick = {
                                                transactionInfoViewModel.annulTransaction(
                                                    receiptId = transaction.receipt,
                                                    rrn = transaction.annulmentCode
                                                )
                                            },
                                            modifier = Modifier.align(Alignment.Center),
                                        ) {
                                            Text(stringResource(id = R.string.annul_transaction))
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    )
}
