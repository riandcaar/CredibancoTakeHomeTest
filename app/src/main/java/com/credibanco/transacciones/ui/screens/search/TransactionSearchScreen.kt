package com.credibanco.transacciones.ui.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionSearchScreen(
    transactionSearchViewModel: TransactionSearchViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by transactionSearchViewModel.uiState.collectAsState()

    var receipt by remember { mutableStateOf("") }

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
                            (stringResource(id = R.string.search_transaction)),
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
                    .padding(top = paddingValues.calculateTopPadding() + 60.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = receipt,
                        onValueChange = { receipt = it },
                        label = { Text((stringResource(id = R.string.receipt_id))) }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = {
                            Log.d(
                                "TransactionSearchScreen",
                                "Attempting to navigate with receipt: $receipt"
                            )
                            navController.navigate("${Routes.TransactionInfo}/${receipt}")
                        },
                            modifier = Modifier.align(Alignment.Center)
                            ) {
                            Text((stringResource(id = R.string.Search)))
                        }
                    }

                }
            }
        }
    )
}