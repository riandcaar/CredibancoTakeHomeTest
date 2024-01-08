package com.credibanco.transacciones.ui.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.collectAsState
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
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text("Search Transaction") }
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
                        value = receipt,
                        onValueChange = { receipt = it },
                        label = { Text("Receipt ID") }
                    )
                    Button(onClick = {
                        Log.d("TransactionSearchScreen", "Attempting to navigate with receipt: $receipt")
                        navController.navigate("${Routes.TransactionInfo}/${receipt}")
                    }) {
                        Text("Search")
                    }

                }
            }
        }
    )
}