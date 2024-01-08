package com.credibanco.transacciones.ui.screens.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.credibanco.transacciones.R
import com.credibanco.transacciones.ui.components.CardTransactionInfo
import com.credibanco.transacciones.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreen(
    transactionListViewModel: TransactionListViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by transactionListViewModel.uiState.collectAsState()

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
                title = { Text("Transaction List") }
            )
        },
        content = { paddingValues ->
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                when {
                    uiState.loading -> {
                        CircularProgressIndicator()
                    }
                    uiState.error != null -> {
                        Text(text = "Error: ${uiState.error}")
                    }
                    uiState.transactions.isNullOrEmpty() -> {
                        Text(text = "No transactions available")
                    }
                    else -> {
                        LazyColumn {
                            items(uiState.transactions!!) { transaction ->
                                CardTransactionInfo(
                                    transaction = transaction,
                                    onClick = { navController.navigate("${Routes.TransactionInfo}/${transaction.receipt}") }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}