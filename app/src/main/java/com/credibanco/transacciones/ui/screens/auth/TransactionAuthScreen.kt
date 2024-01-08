package com.credibanco.transacciones.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.credibanco.transacciones.R
import com.credibanco.transacciones.ui.navigation.Routes
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionAuthScreen(
    transactionAuthViewModel: TransactionAuthViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by transactionAuthViewModel.uiState.collectAsState()

    var showMessage by remember { mutableStateOf(true) }


    var idInput by remember { mutableStateOf("") }
    var commerceCodeInput by remember { mutableStateOf("") }
    var terminalCodeInput by remember { mutableStateOf("") }
    var amountInput by remember { mutableStateOf("") }
    var cardInput by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(R.color.credibanco)
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(Modifier.weight(1f))
                        Text(
                            (stringResource(id = R.string.portal_transacciones)),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.weight(1f))
                    }
                }
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.TransactionList) },
                    modifier = Modifier.padding(end = 16.dp, start = 65.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_transaction_list),
                        contentDescription = "Transaction List"
                    )
                }
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.SearchTransaction) },
                    modifier = Modifier.padding(end = 30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Transaction"
                    )
                }
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding() + 50.dp,
                        bottom = 30.dp,
                        start = 60.dp,
                        end = 60.dp
                    )
            ) {
                when {
                    uiState.loading -> {
                        CircularProgressIndicator()
                    }

                    else -> {
                        Column(modifier = Modifier.align(Alignment.Center)) {
                            Text(stringResource(id = R.string.id),
                                fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = idInput,
                                onValueChange = { idInput = it },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(stringResource(id = R.string.commerce_code),
                                fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = commerceCodeInput,
                                onValueChange = { commerceCodeInput = it },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(stringResource(id = R.string.terminal_code),
                                    fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = terminalCodeInput,
                                onValueChange = { terminalCodeInput = it },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(stringResource(id = R.string.amount),
                                fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = amountInput,
                                onValueChange = { amountInput = it },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(stringResource(id = R.string.card),
                                fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = cardInput,
                                onValueChange = { cardInput = it },
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(
                                onClick = {
                                    showMessage = true
                                    transactionAuthViewModel.authorizeTransaction(
                                        amountInput
                                    )
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(stringResource(id = R.string.validar_transaccion))
                            }
                            if (showMessage) {
                                Spacer(modifier = Modifier.height(16.dp))
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    when (uiState.transactionResult.status) {
                                        stringResource(id = R.string.aprobada) -> Text(
                                            text = stringResource(id = R.string.transaccion_exitosa),
                                            modifier = Modifier.align(Alignment.Center),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )

                                        stringResource(id = R.string.denegada) -> Text(
                                            text = stringResource(id = R.string.transaccion_denegada),
                                            modifier = Modifier.align(Alignment.Center),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
    LaunchedEffect(uiState.transactionResult.status) {
        if (uiState.transactionResult.status == "00" || uiState.transactionResult.status == "99") {
            delay(2000)
            showMessage = false
        }
    }
}